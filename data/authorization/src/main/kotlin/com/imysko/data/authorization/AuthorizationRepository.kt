package com.imysko.data.authorization

import kotlinx.coroutines.flow.StateFlow

interface AuthorizationRepository {

    val isAuthorize: StateFlow<Boolean>

    suspend fun sendCodeOnEmail(email: String)

    suspend fun authorize(email: String, code: String): Boolean
}
