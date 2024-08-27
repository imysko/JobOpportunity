package com.imysko.features.authorization.di

import com.imysko.features.authorization.presentation.screens.codeConfirmation.CodeConfirmationFragment
import com.imysko.features.authorization.presentation.screens.homeAuthorization.HomeAuthorizationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BindAuthorizationUseCase::class],
    dependencies = [AuthorizationDeps::class],
)
internal interface AuthorizationComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: AuthorizationDeps): Builder

        fun build(): AuthorizationComponent
    }

    fun inject(fragment: HomeAuthorizationFragment)
    fun inject(fragment: CodeConfirmationFragment)
}
