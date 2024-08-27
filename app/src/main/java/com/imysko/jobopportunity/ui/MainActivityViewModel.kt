package com.imysko.jobopportunity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.imysko.data.authorization.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModel(
    private val authorizationRepository: AuthorizationRepository,
) : ViewModel() {

    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean>
        get() = _isAuthorized.asStateFlow()

    init {
        getAuthorizedState()
    }

    private fun getAuthorizedState() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                authorizationRepository.isAuthorize.collectLatest { isAuthorized ->
                    _isAuthorized.update { isAuthorized }
                }
            }
        }
    }

    class Factory @Inject constructor(
        private val authorizationRepository: Provider<AuthorizationRepository>,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainActivityViewModel::class.java)
            return MainActivityViewModel(authorizationRepository.get()) as T
        }
    }
}
