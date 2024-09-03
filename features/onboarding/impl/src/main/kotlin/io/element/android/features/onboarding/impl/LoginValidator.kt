package io.element.android.features.onboarding.impl

import androidx.annotation.StringRes

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
