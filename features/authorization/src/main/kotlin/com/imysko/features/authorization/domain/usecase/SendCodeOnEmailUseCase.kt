package com.imysko.features.authorization.domain.usecase

import com.imysko.features.authorization.domain.repositories.AuthorizationRepository
import javax.inject.Inject

interface SendCodeOnEmailUseCase {

    suspend operator fun invoke(email: String)
}

internal class SendCodeOnEmailUseCaseImpl @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) : SendCodeOnEmailUseCase {

    override suspend fun invoke(email: String) {
        authorizationRepository.sendCodeOnEmail(email)
    }
}
