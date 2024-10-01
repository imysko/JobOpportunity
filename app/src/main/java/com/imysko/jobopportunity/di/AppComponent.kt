package com.imysko.jobopportunity.di

import android.content.Context
import com.imysko.common.database.AppDatabaseProvider
import com.imysko.data.vacancies.di.VacanciesRepositoryProvider
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import com.imysko.features.favorite.di.FavoriteVacanciesDeps
import com.imysko.features.searchVacancy.di.SearchVacancyDeps
import com.imysko.jobopportunity.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppDatabaseProvider::class,
        VacanciesRepositoryProvider::class,
        BindAppUseCase::class,
    ],
)
interface AppComponent : SearchVacancyDeps, FavoriteVacanciesDeps {

    override val appContext: Context
    override val vacanciesRepository: VacanciesRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
