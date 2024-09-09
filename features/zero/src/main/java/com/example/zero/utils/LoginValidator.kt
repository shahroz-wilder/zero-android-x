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

package com.example.zero.utils

import androidx.annotation.StringRes
import com.example.zero.R

class LoginValidator(
	@StringRes val emailError: Int? = null,
	@StringRes val passwordError: Int? = null,
	val isDataValid: Boolean = true
) {
	companion object {
		fun empty() = LoginValidator()

		fun setError(emailError: Int?, passwordError: Int?) =
			LoginValidator(
				emailError = emailError,
				passwordError = passwordError,
				isDataValid = emailError == null && passwordError == null
			)

		fun setError(errorCode: String?): LoginValidator {
			return when {
				errorCode?.equals("INVALID_EMAIL_PASSWORD") == true ->
					setError(emailError = null, passwordError = R.string.error_incorrect_email_password)
				errorCode?.isNotEmpty() == true ->
					setError(emailError = null, passwordError = R.string.error_generic_authorise)
				else -> empty()
			}
		}
	}
}
