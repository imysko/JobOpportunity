package com.imysko.features.authorization.presentation.screens.homeAuthorization

data class HomeAuthorizationUiState(
    val emailInput: String = "",
    val continueButtonEnabled: Boolean = false,
    val emailIncorrect: Boolean = false,
)
