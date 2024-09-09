package com.example.zero.states

import androidx.annotation.StringRes

data class AuthScreenUiState(val state: AuthUiState)

sealed interface AuthUiState {
	object Success : AuthUiState

	data class Error(@StringRes val error: Int, val isWalletConnectionError: Boolean = false) :
        AuthUiState

	object Loading : AuthUiState

	object Required : AuthUiState

	object CompleteProfile : AuthUiState

	object Result : AuthUiState
}
