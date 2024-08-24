package com.imysko.features.authorization.di

import com.imysko.features.authorization.data.repositories.MockAuthorizationRepository
import com.imysko.features.authorization.domain.repositories.AuthorizationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AuthorizationRepositoryModule {

    @Singleton
    @Provides
    fun provideMockAuthorizationRepository(): AuthorizationRepository =
        MockAuthorizationRepository()
}
