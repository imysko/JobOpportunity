package com.imysko.features.authorization.presentation.screens.homeAuthorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.authorization.domain.usecase.SendCodeOnEmailUseCase
import javax.inject.Inject
import javax.inject.Provider

class HomeAuthorizationViewModelFactory @Inject constructor(
    private val sendCodeOnEmailUseCase: Provider<SendCodeOnEmailUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == HomeAuthorizationViewModel::class.java)
        return HomeAuthorizationViewModel(sendCodeOnEmailUseCase.get()) as T
    }
}
