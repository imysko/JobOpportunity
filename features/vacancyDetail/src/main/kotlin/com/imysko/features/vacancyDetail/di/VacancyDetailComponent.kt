package com.imysko.features.vacancyDetail.di

import com.imysko.data.vacancies.di.VacanciesRepositoryProvider
import com.imysko.features.vacancyDetail.presentation.VacancyDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        VacanciesRepositoryProvider::class,
        BindVacancyDetailUseCase::class,
    ],
    dependencies = [VacancyDetailDeps::class]
)
internal interface VacancyDetailComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: VacancyDetailDeps): Builder

        fun build(): VacancyDetailComponent
    }

    fun inject(fragment: VacancyDetailFragment)
}
