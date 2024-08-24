package com.imysko.features.authorization.di

import com.imysko.features.authorization.presentation.screens.homeAuthorization.HomeAuthorizationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthorizationRepositoryModule::class,
        AuthorizationUseCaseModule::class,
    ]
)
interface AuthorizationComponent {

    @Component.Builder
    interface Builder {
        fun build(): AuthorizationComponent
    }

    fun inject(fragment: HomeAuthorizationFragment)
}