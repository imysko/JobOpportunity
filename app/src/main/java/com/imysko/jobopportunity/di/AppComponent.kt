package com.imysko.jobopportunity.di

import com.imysko.data.authorization.AuthorizationRepository
import com.imysko.data.authorization.di.AuthorizationRepositoryProvider
import com.imysko.features.authorization.di.AuthorizationDeps
import com.imysko.jobopportunity.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AuthorizationRepositoryProvider::class],
)
interface AppComponent : AuthorizationDeps {

    override val authorizationRepository: AuthorizationRepository

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
