package com.imysko.features.authorization.domain.usecase

import com.imysko.features.authorization.domain.repositories.AuthorizationRepository
import javax.inject.Inject

interface AuthorizeUseCase {

    suspend operator fun invoke(email: String, code: String): Boolean
}

internal class AuthorizeUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : AuthorizeUseCase {

    override suspend fun invoke(email: String, code: String): Boolean {
        return authorizationRepository.authorize(email, code)
    }
}
