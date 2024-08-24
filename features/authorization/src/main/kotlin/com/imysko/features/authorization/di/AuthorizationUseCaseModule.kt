package com.imysko.features.authorization.di

import com.imysko.features.authorization.domain.usecase.AuthorizeUseCase
import com.imysko.features.authorization.domain.usecase.AuthorizeUseCaseImpl
import com.imysko.features.authorization.domain.usecase.SendCodeOnEmailUseCase
import com.imysko.features.authorization.domain.usecase.SendCodeOnEmailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class AuthorizationUseCaseModule {

    @Binds
    abstract fun bindSendCodeOnEmailUseCase(
        sendCodeOnEmailUseCaseImpl: SendCodeOnEmailUseCaseImpl,
    ): SendCodeOnEmailUseCase

    @Binds
    abstract fun bindAuthorizeUseCase(
        authorizeUseCaseImpl: AuthorizeUseCaseImpl,
    ): AuthorizeUseCase
}
