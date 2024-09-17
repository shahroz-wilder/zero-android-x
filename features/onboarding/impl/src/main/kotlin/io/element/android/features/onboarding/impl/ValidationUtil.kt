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

package io.element.android.features.onboarding.impl

import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtil {
    private val EMAIL_PATTERN = Patterns.EMAIL_ADDRESS
    private val PASSWORD_PATTERN =
        Pattern.compile(
            "^" +
                "(?=.*[0-9])" + // at least 1 digit
                "(?=.*[a-z])" + // at least 1 lower case letter
                "(?=.*[A-Z])" + // at least 1 upper case letter
                "(?=.*[a-zA-Z])" + // any letter
                // "(?=.*[!@#$%^&*])" + // at least 1 special character
                "(?=\\S+$)" + // no white spaces
                ".{8,}" + // at least 8 characters
                "$"
        )

    fun validateInput(
        input: String?,
        checkLength: Boolean = false,
        minLength: Int = 3,
        error: Int = R.string.field_required
    ): Int? {
        return when {
            input.isNullOrEmpty() -> error
            checkLength && !matchesMinLength(input, minLength = minLength) ->
                R.string.error_user_name_length
            else -> null
        }
    }

    fun matchesMinLength(input: String?, minLength: Int = 0) = (input?.length ?: 0) >= minLength

    fun matchesMaxLength(input: String?, maxLength: Int = 0) = (input?.length ?: 0) <= maxLength

    fun liesInLengthRange(min: Int = 0, max: Int = Int.MAX_VALUE, input: String?): Int? {
        return when {
            !matchesMinLength(input, min) -> R.string.error_user_name_length
            !matchesMaxLength(input, max) -> R.string.error_user_name_max_length
            else -> null
        }
    }

    fun validateEmail(email: String?): Int? {
        return when {
            email.isNullOrEmpty() -> R.string.email_required
            !EMAIL_PATTERN.matcher(email).matches() -> R.string.error_invalid_email
            else -> null
        }
    }

    fun validatePassword(password: String?, checkPasswordRegex: Boolean = true): Int? {
        return if (password.isNullOrEmpty()) R.string.password_required
        else if (checkPasswordRegex && !PASSWORD_PATTERN.matcher(password).matches()) {
            R.string.password_criteria
        } else null
    }

    fun validatePasswordMatch(password: String?, confirmPassword: String?): Int? {
        return if (password.equals(confirmPassword, true)) null else R.string.password_mismatch_error
    }
}
