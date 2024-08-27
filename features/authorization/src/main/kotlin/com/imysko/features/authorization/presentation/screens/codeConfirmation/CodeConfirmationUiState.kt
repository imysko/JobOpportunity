package com.imysko.features.authorization.presentation.screens.codeConfirmation

data class CodeConfirmationUiState(
    val email: String = "",
    val confirmButtonEnabled: Boolean = false,
    val firstDigit: String? = null,
    val secondDigit: String? = null,
    val thirdDigit: String? = null,
    val forthDigit: String? = null,
)
