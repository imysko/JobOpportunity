package com.imysko.data.authorization

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class MockAuthorizationRepository : AuthorizationRepository {

    private val _isAuthorize: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isAuthorize: StateFlow<Boolean>
        get() = _isAuthorize.asStateFlow()

    override suspend fun sendCodeOnEmail(email: String) = Unit

    override suspend fun authorize(email: String, code: String): Boolean {
        _isAuthorize.update { true }
        return true
    }
}
