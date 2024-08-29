package com.imysko.jobopportunity.di

import android.content.Context
import com.imysko.data.authorization.AuthorizationRepository
import com.imysko.data.authorization.di.AuthorizationRepositoryProvider
import com.imysko.features.authorization.di.AuthorizationDeps
import com.imysko.features.searchVacancy.di.SearchVacancyDeps
import com.imysko.features.vacancyDetail.di.VacancyDetailDeps
import com.imysko.jobopportunity.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AuthorizationRepositoryProvider::class],
)
interface AppComponent : AuthorizationDeps, SearchVacancyDeps, VacancyDetailDeps {

    override val authorizationRepository: AuthorizationRepository
    override val appContext: Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
