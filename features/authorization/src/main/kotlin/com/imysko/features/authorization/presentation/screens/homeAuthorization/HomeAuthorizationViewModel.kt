package com.imysko.features.authorization.presentation.screens.homeAuthorization

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.features.authorization.domain.usecase.SendCodeOnEmailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeAuthorizationViewModel(
    private val sendCodeOnEmailUseCase: SendCodeOnEmailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeAuthorizationUiState())
    val uiState: StateFlow<HomeAuthorizationUiState>
        get() = _uiState.asStateFlow()

    private val _emailInput = MutableStateFlow("")
    val emailInput: String
        get() = _emailInput.value

    fun onEmailChange(email: String) {
        _emailInput.update { email }

        _uiState.update {
            it.copy(
                emailInput = email,
                continueButtonEnabled = email.any(),
                emailIncorrect = false,
            )
        }
    }

    fun validateEmail(): Boolean {
        val isEmailCorrect = Patterns.EMAIL_ADDRESS.matcher(_emailInput.value).matches()

        Patterns.EMAIL_ADDRESS

        _uiState.update { it.copy(emailIncorrect = !isEmailCorrect) }

        return isEmailCorrect
    }

    fun sendCodeOnEmail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                sendCodeOnEmailUseCase.invoke(_emailInput.value)
            }
        }
    }
}
