package com.imysko.features.authorization.presentation.screens.codeConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.features.authorization.domain.usecase.AuthorizeUseCase
import com.imysko.features.authorization.presentation.screens.codeConfirmation.entities.CodeConfirmationDigitIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CodeConfirmationViewModel(
    private val email: String,
    private val authorizeUseCase: AuthorizeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CodeConfirmationUiState(email = email))
    val uiState: StateFlow<CodeConfirmationUiState>
        get() = _uiState.asStateFlow()

    private val _codeConfirmation: MutableList<String?> = MutableList(4) { null }

    fun appendDigit(index: Int, digit: String) {
        _codeConfirmation[index] = digit
        updateCodeUiState()
    }

    fun removeDigit(index: Int) {
        if (_codeConfirmation.size > index) {
            _codeConfirmation[index] = null
            updateCodeUiState()
        }
    }

    private fun updateCodeUiState() {
        _uiState.update {
            it.copy(
                firstDigit = _codeConfirmation.getOrNull(CodeConfirmationDigitIndex.FIRST.index),
                secondDigit = _codeConfirmation.getOrNull(CodeConfirmationDigitIndex.SECOND.index),
                thirdDigit = _codeConfirmation.getOrNull(CodeConfirmationDigitIndex.THIRD.index),
                forthDigit = _codeConfirmation.getOrNull(CodeConfirmationDigitIndex.FORTH.index),
                confirmButtonEnabled = _codeConfirmation.size == CODE_CONFIRMATION_SIZE,
            )
        }
    }

    fun authorize() {
        viewModelScope.launch {
            authorizeUseCase.invoke(email = email, code = _codeConfirmation.joinToString(""))
        }
    }

    companion object {
        const val CODE_CONFIRMATION_SIZE = 4
    }
}
