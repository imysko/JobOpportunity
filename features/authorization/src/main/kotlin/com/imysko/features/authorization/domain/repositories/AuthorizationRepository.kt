package com.imysko.features.authorization.domain.repositories

import kotlinx.coroutines.flow.StateFlow

interface AuthorizationRepository {

    val isAuthorize: StateFlow<Boolean>

    suspend fun sendCodeOnEmail(email: String)

    suspend fun authorize(email: String, code: String): Boolean
}
