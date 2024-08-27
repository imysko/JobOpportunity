package com.imysko.data.authorization.di

import com.imysko.data.authorization.AuthorizationRepository
import com.imysko.data.authorization.MockAuthorizationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AuthorizationRepositoryProvider {

    @Singleton
    @Provides
    fun provideMockAuthorizationRepository(): AuthorizationRepository =
        MockAuthorizationRepository()
}
