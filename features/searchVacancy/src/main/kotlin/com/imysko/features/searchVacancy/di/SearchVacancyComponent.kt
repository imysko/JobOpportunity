package com.imysko.features.searchVacancy.di

import com.imysko.data.offers.di.OffersRepositoryProvider
import com.imysko.data.vacancies.di.VacanciesRepositoryProvider
import com.imysko.features.searchVacancy.presentation.SearchVacancyFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        OffersRepositoryProvider::class,
        VacanciesRepositoryProvider::class,
        BindSearchVacancyUseCase::class,
    ],
    dependencies = [SearchVacancyDeps::class]
)
internal interface SearchVacancyComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: SearchVacancyDeps): Builder

        fun build(): SearchVacancyComponent
    }

    fun inject(fragment: SearchVacancyFragment)
}
