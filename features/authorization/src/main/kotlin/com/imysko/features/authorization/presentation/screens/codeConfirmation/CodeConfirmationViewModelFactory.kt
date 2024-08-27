package com.imysko.features.authorization.presentation.screens.codeConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.authorization.domain.usecase.AuthorizeUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Provider

class CodeConfirmationViewModelFactory @AssistedInject constructor(
    @Assisted("email") private val email: String,
    private val authorizeUseCase: Provider<AuthorizeUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CodeConfirmationViewModel::class.java)
        return CodeConfirmationViewModel(email, authorizeUseCase.get()) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("email") email: String): CodeConfirmationViewModelFactory
    }
}
