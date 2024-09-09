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

package io.element.android.libraries.matrix.impl.auth

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface ZeroAuthService {
    @POST(value = "api/v2/accounts/login")
    suspend fun authorise(@Body payload: AuthoriseUserRequest): ZeroAuthCredentials

    @GET(value = "accounts/ssoToken")
    suspend fun getSSOToken(): ZeroSSOToken

    @POST
    suspend fun fetchMatrixSession(
        @Url url:String,
        @Header("Host") host: String,
        @Header("Origin") origin: String = "https://zos.zero.tech",//AuthConstants.origin,
        @Body params: Param
    ): ZeroMatrixAuthCredentials
}
