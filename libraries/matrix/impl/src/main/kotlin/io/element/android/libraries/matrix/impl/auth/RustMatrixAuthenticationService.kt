/*
 * Copyright 2023, 2024 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE in the repository root for full details.
 */

package io.element.android.libraries.matrix.impl.auth

import com.squareup.anvil.annotations.ContributesBinding
import io.element.android.libraries.core.coroutine.CoroutineDispatchers
import io.element.android.libraries.core.extensions.mapFailure
import io.element.android.libraries.di.AppScope
import io.element.android.libraries.di.SingleIn
import io.element.android.libraries.matrix.api.MatrixClient
import io.element.android.libraries.matrix.api.auth.MatrixAuthenticationService
import io.element.android.libraries.matrix.api.auth.MatrixHomeServerDetails
import io.element.android.libraries.matrix.api.auth.OidcDetails
import io.element.android.libraries.matrix.api.auth.qrlogin.MatrixQrCodeLoginData
import io.element.android.libraries.matrix.api.auth.qrlogin.QrCodeLoginStep
import io.element.android.libraries.matrix.api.core.SessionId
import io.element.android.libraries.matrix.impl.RustMatrixClientFactory
import io.element.android.libraries.matrix.impl.auth.qrlogin.QrErrorMapper
import io.element.android.libraries.matrix.impl.auth.qrlogin.SdkQrCodeLoginData
import io.element.android.libraries.matrix.impl.auth.qrlogin.toStep
import io.element.android.libraries.matrix.impl.exception.mapClientException
import io.element.android.libraries.matrix.impl.keys.PassphraseGenerator
import io.element.android.libraries.matrix.impl.mapper.toSessionData
import io.element.android.libraries.matrix.impl.paths.SessionPaths
import io.element.android.libraries.matrix.impl.paths.SessionPathsFactory
import io.element.android.libraries.sessionstorage.api.LoggedInState
import io.element.android.libraries.sessionstorage.api.LoginType
import io.element.android.libraries.sessionstorage.api.SessionStore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.matrix.rustcomponents.sdk.Client
import org.matrix.rustcomponents.sdk.HumanQrLoginException
import org.matrix.rustcomponents.sdk.QrCodeDecodeException
import org.matrix.rustcomponents.sdk.QrLoginProgress
import org.matrix.rustcomponents.sdk.QrLoginProgressListener
import org.matrix.rustcomponents.sdk.Session
import org.matrix.rustcomponents.sdk.use
import timber.log.Timber
import uniffi.matrix_sdk.OidcAuthorizationData
import javax.inject.Inject

@ContributesBinding(AppScope::class)
@SingleIn(AppScope::class)
class RustMatrixAuthenticationService @Inject constructor(
    private val sessionPathsFactory: SessionPathsFactory,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val sessionStore: SessionStore,
    private val rustMatrixClientFactory: RustMatrixClientFactory,
    private val passphraseGenerator: PassphraseGenerator,
    private val oidcConfigurationProvider: OidcConfigurationProvider,
    private val zeroAuthService: ZeroAuthService
) : MatrixAuthenticationService {
    // Passphrase which will be used for new sessions. Existing sessions will use the passphrase
    // stored in the SessionData.
    private val pendingPassphrase = getDatabasePassphrase()

    // Need to keep a copy of the current session path to eventually delete it.
    // Ideally it would be possible to get the sessionPath from the Client to avoid doing this.
    private var sessionPaths: SessionPaths? = null
    private var currentClient: Client? = null
    private var currentHomeserver = MutableStateFlow<MatrixHomeServerDetails?>(null)

    private fun rotateSessionPath(): SessionPaths {
        sessionPaths?.deleteRecursively()
        return sessionPathsFactory.create()
            .also { sessionPaths = it }
    }

    override fun loggedInStateFlow(): Flow<LoggedInState> {
        return sessionStore.isLoggedIn()
    }

    override suspend fun getLatestSessionId(): SessionId? = withContext(coroutineDispatchers.io) {
        sessionStore.getLatestSession()?.userId?.let { SessionId(it) }
    }

    override suspend fun restoreSession(sessionId: SessionId): Result<MatrixClient> = withContext(coroutineDispatchers.io) {
        runCatching {
            val sessionData = sessionStore.getSession(sessionId.value)
            if (sessionData != null) {
                if (sessionData.isTokenValid) {
                    // Use the sessionData.passphrase, which can be null for a previously created session
                    if (sessionData.passphrase == null) {
                        Timber.w("Restoring a session without a passphrase")
                    } else {
                        Timber.w("Restoring a session with a passphrase")
                    }
                    rustMatrixClientFactory.create(sessionData)
                } else {
                    error("Token is not valid")
                }
            } else {
                error("No session to restore with id $sessionId")
            }
        }.mapFailure { failure ->
            failure.mapClientException()
        }
    }

    private fun getDatabasePassphrase(): String? {
        val passphrase = passphraseGenerator.generatePassphrase()
        if (passphrase != null) {
            Timber.w("New sessions will be encrypted with a passphrase")
        }
        return passphrase
    }

    override fun getHomeserverDetails(): StateFlow<MatrixHomeServerDetails?> = currentHomeserver

    override suspend fun setHomeserver(homeserver: String): Result<Unit> =
        withContext(coroutineDispatchers.io) {
            val emptySessionPath = rotateSessionPath()
            runCatching {
                val client = getBaseClientBuilder(emptySessionPath)
                    .serverNameOrHomeserverUrl(homeserver)
                    .build()
                currentClient = client
                val homeServerDetails = client.homeserverLoginDetails().map()
                currentHomeserver.value = homeServerDetails.copy(url = homeserver)
            }.onFailure {
                clear()
            }.mapFailure { failure ->
                failure.mapAuthenticationException()
            }
        }

    override suspend fun login(username: String, password: String): Result<SessionId> =
        withContext(coroutineDispatchers.io) {
            runSafeCall {
                val client = currentClient ?: error("You need to call `setHomeserver()` first")
                val currentSessionPath = sessionPath ?: error("You need to call `setHomeserver()` first")
                val payload = AuthoriseUserRequest.newRequest(username, password)
                val zeroAuthRequest = zeroAuthService.authorise(payload)
//                preferences.setZeroAuthCredential(zeroAuthRequest.toModel())
                val authCredentials = proceedLoginFlow()


                val matrixSession = Session(
                    accessToken = authCredentials.accessToken,
                    refreshToken = null,
                    userId = authCredentials.userId,
                    deviceId = authCredentials.deviceId,
                    homeserverUrl = authCredentials.homeServer,
                    oidcData = null,
                    slidingSyncProxy = null
                )
                client.restoreSession(matrixSession)
                val sessionData = client.session()
                    .toSessionData(
                        isTokenValid = true,
                        loginType = LoginType.PASSWORD,
                        passphrase = pendingPassphrase,
                        sessionPath = currentSessionPath.absolutePath,
                    )
                clear()
                sessionStore.storeData(sessionData)
                Result.success(SessionId(sessionData.userId))

                //val authCredentials = proceedLoginFlow(zeroAuthRequest)
                //authCredentials.toModel()
            }
        }
     /*   withContext(coroutineDispatchers.io) {
            runCatching {
                val client = currentClient ?: error("You need to call `setHomeserver()` first")
                val currentSessionPaths = sessionPaths ?: error("You need to call `setHomeserver()` first")
                client.login(username, password, "Element X Android", null)
                val sessionData = client.session()
                    .toSessionData(
                        isTokenValid = true,
                        loginType = LoginType.PASSWORD,
                        passphrase = pendingPassphrase,
                        sessionPaths = currentSessionPaths,
                    )
                clear()
                sessionStore.storeData(sessionData)
                SessionId(sessionData.userId)
            }.mapFailure { failure ->
                failure.mapAuthenticationException()
            }
        }
*/
    private var pendingOidcAuthorizationData: OidcAuthorizationData? = null

    private suspend fun proceedLoginFlow(): ZeroMatrixAuthCredentials {
//        preferences.setZeroAuthCredential(authCredentials.toModel())
        val ssoRequest = zeroAuthService.getSSOToken()

        //getch mattix session and resilt is [ut here
        return fetchMatrixSession(ssoRequest.token)
//        return authService.createSessionFromSSO(clientAuthorisationRequest)
    }

    private suspend fun fetchMatrixSession(token: String): ZeroMatrixAuthCredentials {
        val homeAddress = "https://zos-home-2-e24b9412096f.herokuapp.com"//"https://zero-synapse-development-db365bf96189.herokuapp.com"//appSettings.defaultHomeServerAddress
        val url = "$homeAddress/${MATRIX_API_AUTH}"

        val host = if (homeAddress.startsWith("https://")) {
            homeAddress.substringAfter("https://")
        } else {
            homeAddress
        }

        val parameters = mapOf(
            "token" to token,
            "type" to "org.matrix.login.jwt"//AuthConstants.ssoTokenType
        )
        val param = Param(token,"org.matrix.login.jwt")

        val headers = mapOf(
            "Host" to host,
            "Origin" to "https://zos.zero.tech"//AuthConstants.origin
        )

        return zeroAuthService.fetchMatrixSession(url,host, params = param)//parameters)
        //zeroAuthService.matrixClientAuthorisation(
        //        url = url,
         //       payload = MatrixClientAuthRequest.newRequest(token)
           // )
    }

    override suspend fun getOidcUrl(): Result<OidcDetails> {
        return withContext(coroutineDispatchers.io) {
            runCatching {
                val client = currentClient ?: error("You need to call `setHomeserver()` first")
                val oidcAuthenticationData = client.urlForOidcLogin(oidcConfigurationProvider.get())
                val url = oidcAuthenticationData.loginUrl()
                pendingOidcAuthorizationData = oidcAuthenticationData
                OidcDetails(url)
            }.mapFailure { failure ->
                failure.mapAuthenticationException()
            }
        }
    }

    override suspend fun cancelOidcLogin(): Result<Unit> {
        return withContext(coroutineDispatchers.io) {
            runCatching {
                pendingOidcAuthorizationData?.close()
                pendingOidcAuthorizationData = null
            }.mapFailure { failure ->
                failure.mapAuthenticationException()
            }
        }
    }

    /**
     * callbackUrl should be the uriRedirect from OidcClientMetadata (with all the parameters).
     */
    override suspend fun loginWithOidc(callbackUrl: String): Result<SessionId> {
        return withContext(coroutineDispatchers.io) {
            runCatching {
                val client = currentClient ?: error("You need to call `setHomeserver()` first")
                val currentSessionPaths = sessionPaths ?: error("You need to call `setHomeserver()` first")
                val urlForOidcLogin = pendingOidcAuthorizationData ?: error("You need to call `getOidcUrl()` first")
                client.loginWithOidcCallback(urlForOidcLogin, callbackUrl)
                val sessionData = client.session().toSessionData(
                    isTokenValid = true,
                    loginType = LoginType.OIDC,
                    passphrase = pendingPassphrase,
                    sessionPaths = currentSessionPaths,
                )
                clear()
                pendingOidcAuthorizationData?.close()
                pendingOidcAuthorizationData = null
                sessionStore.storeData(sessionData)
                SessionId(sessionData.userId)
            }.mapFailure { failure ->
                failure.mapAuthenticationException()
            }
        }
    }

    override suspend fun loginWithQrCode(qrCodeData: MatrixQrCodeLoginData, progress: (QrCodeLoginStep) -> Unit) =
        withContext(coroutineDispatchers.io) {
            val emptySessionPaths = rotateSessionPath()
            runCatching {
                val client = rustMatrixClientFactory.getBaseClientBuilder(
                    sessionPaths = emptySessionPaths,
                    passphrase = pendingPassphrase,
                    restore = false,
                )
                    .buildWithQrCode(
                        qrCodeData = (qrCodeData as SdkQrCodeLoginData).rustQrCodeData,
                        oidcConfiguration = oidcConfigurationProvider.get(),
                        progressListener = object : QrLoginProgressListener {
                            override fun onUpdate(state: QrLoginProgress) {
                                Timber.d("QR Code login progress: $state")
                                progress(state.toStep())
                            }
                        }
                    )
                client.use { rustClient ->
                    val sessionData = rustClient.session()
                        .toSessionData(
                            isTokenValid = true,
                            loginType = LoginType.QR,
                            passphrase = pendingPassphrase,
                            sessionPaths = emptySessionPaths,
                        )
                    sessionStore.storeData(sessionData)
                    SessionId(sessionData.userId)
                }
            }.mapFailure {
                when (it) {
                    is QrCodeDecodeException -> QrErrorMapper.map(it)
                    is HumanQrLoginException -> QrErrorMapper.map(it)
                    else -> it
                }
            }.onFailure { throwable ->
                if (throwable is CancellationException) {
                    throw throwable
                }
                Timber.e(throwable, "Failed to login with QR code")
            }
        }

    private suspend fun getBaseClientBuilder(
        sessionPaths: SessionPaths,
    ) = rustMatrixClientFactory
        .getBaseClientBuilder(
            sessionPaths = sessionPaths,
            passphrase = pendingPassphrase,
            restore = false,
        )

    private fun clear() {
        currentClient?.close()
        currentClient = null
    }

    private suspend fun <T> runSafeCall(run: suspend () -> T) =
        try {
            run()
        } catch (e: Throwable) {
            throw e
        }

    companion object {
        private const val MATRIX_API_AUTH = "_matrix/client/r0/login"
    }
}
data class Param(val token:String,val type:String)
