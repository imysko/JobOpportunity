package com.imysko.features.favorite.di

import com.imysko.features.favorite.presentation.FavoriteFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BindFavoriteVacanciesUseCase::class],
    dependencies = [FavoriteVacanciesDeps::class]
)
internal interface FavoriteVacanciesComponent {

    @Component.Builder
    interface Builder {

        fun deps(deps: FavoriteVacanciesDeps): Builder

        fun build(): FavoriteVacanciesComponent
    }

    fun inject(fragment: FavoriteFragment)
}
