/*
 * Copyright (c) 2024 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zero

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.zero.components.AuthBackground
import com.example.zero.components.AuthScreenFooter
import com.example.zero.components.ErrorTextBox
import com.example.zero.components.FadeExpandAnimation
import com.example.zero.components.PasswordTextField
import com.example.zero.components.SegmentedControl
import com.example.zero.components.SimpleAppBar
import com.example.zero.components.SimpleInputField
import com.example.zero.components.SuccessTextBox
import com.example.zero.components.ZImageButton
import com.example.zero.theme.AppTheme
import com.example.zero.utils.LoginFlowType
import com.example.zero.utils.LoginValidator
import com.example.zero.utils.SPACING_10X
import com.example.zero.utils.SPACING_2X
import com.example.zero.utils.SPACING_4X
import com.example.zero.utils.SPACING_5X
import com.example.zero.utils.SPACING_6X
import com.example.zero.utils.SPACING_8X

@Composable
fun LoginScreen(
//    bottomSheetNavigator: BottomSheetNavigator,
//    uiState: AuthUiState,
    loginValidator: LoginValidator,
    isWalletConnected: Boolean = false,
    onBack: () -> Unit,
    onLogin: (String, String) -> Unit,
//    onInitiateWalletConnect: () -> Unit,
    onResetPassword: () -> Unit = {},
    isLoading: Boolean
) {
    val context: Context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    val emailError = remember(loginValidator) { mutableStateOf(loginValidator.emailError) }
    val passwordError = remember(loginValidator) { mutableStateOf(loginValidator.passwordError) }

    val loginFlow: MutableState<LoginFlowType> = remember { mutableStateOf(LoginFlowType.WEB3) }
    val showWeb3LoginUI = loginFlow.value == LoginFlowType.WEB3

//    ModalBottomSheetLayout(/*bottomSheetNavigator = bottomSheetNavigator*/) {

    AuthBackground(isLoading = isLoading/*uiState == AuthUiState.Loading*/) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()) {
                    Column(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.size(SPACING_10X.dp))
                        Spacer(modifier = Modifier.size(SPACING_10X.dp))
                        Spacer(modifier = Modifier.size(SPACING_5X.dp))

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            SegmentedControl(
                                defaultSelectedItemIndex = LoginFlowType.indexOf(loginFlow.value),
                                controlWidth = 230.dp,
                                items =
                                listOf(
                                    context.getString(R.string.web3),
                                    context.getString(R.string.email)
                                ),
                                onItemSelection = {
                                    loginFlow.value = LoginFlowType.get(it)
                                }
                            )
                        }
                        if (showWeb3LoginUI) {
                            Spacer(modifier = Modifier.size(SPACING_6X.dp))
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            // Wallet Connection Login UI
                            FadeExpandAnimation(visible = showWeb3LoginUI) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.size(SPACING_4X.dp))
                                    ZImageButton(
                                        image = R.drawable.img_btn_connect_wallet,
                                        text = stringResource(id = R.string.connect_a_wallet)
                                    ) {
//                                        onInitiateWalletConnect.invoke()
                                    }
                                   /* if (uiState is AuthUiState.Error && uiState.isWalletConnectionError) {
                                        Spacer(modifier = Modifier.size(SPACING_6X.dp))
                                        ErrorTextBox(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 65.dp),
                                            text = stringResource(uiState.error)
                                        )
                                    }*/
                                    if (isWalletConnected) {
                                        Spacer(modifier = Modifier.size(SPACING_6X.dp))
                                        SuccessTextBox(text = stringResource(id = R.string.wallet_connected))
                                    }
                                }
                            }

                            // Email Login UI
                            FadeExpandAnimation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                visible = !showWeb3LoginUI
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.size(SPACING_8X.dp))
                                    SimpleInputField(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = email,
                                        placeholder = R.string.email_address,
                                        onTextChanged = {
                                            email = it
                                            emailError.value = null
                                        },
                                        keyboardOptions =
                                        KeyboardOptions(
                                            keyboardType = KeyboardType.Email,
                                            imeAction = ImeAction.Next
                                        )
                                    )
                                    emailError.value?.let {
                                        Spacer(modifier = Modifier.size(SPACING_2X.dp))
                                        ErrorTextBox(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = stringResource(id = it)
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(SPACING_6X.dp))
                                    PasswordTextField(
                                        modifier = Modifier.fillMaxWidth(),
                                        placeHolder = R.string.password,
                                        error = passwordError.value,
                                        onTextChanged = {
                                            password = it
                                            passwordError.value = null
                                        },
                                        onKeyboardActionDone = {
                                            focusManager.clearFocus()
                                            onLogin.invoke(email.trim(), password.trim())
                                        },
                                        iconTint = AppTheme.colors.colorTextPrimary.copy(alpha = 0.75f)
                                    )
                                   /* val genericError =
                                        passwordError.value ?: (uiState as? AuthUiState.Error)?.error
                                    genericError?.let {
                                        Spacer(modifier = Modifier.size(SPACING_2X.dp))
                                        ErrorTextBox(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = stringResource(id = it)
                                        )
                                    }*/
                                    Spacer(modifier = Modifier.size(SPACING_10X.dp))
                                    ZImageButton(
                                        image = R.drawable.img_btn_login,
                                        text = stringResource(id = R.string.log_in),
                                        enabled = email.isNotBlank() && password.isNotBlank(),
                                        onClick = {
                                            focusManager.clearFocus()
                                            onLogin.invoke(email.trim(), password.trim())
                                        }
                                    )
                                    Spacer(modifier = Modifier.size(SPACING_10X.dp))
                                    Box {
                                        AuthScreenFooter(
                                            preText = stringResource(id = R.string.forgot_password),
                                            postText = stringResource(id = R.string.reset),
                                            verticalPaddings = 0.dp,
                                            onClick = {}//onResetPassword
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                SimpleAppBar(
                    modifier = Modifier.align(Alignment.TopStart),
                    title = stringResource(id = R.string.log_in),
                    onBackPress = {}/*onBack*/
                )
            }
        }
//    }
}
