package com.imysko.jobopportunity.di

import com.imysko.jobopportunity.domain.usecase.GetFavoriteVacanciesCountUseCase
import com.imysko.jobopportunity.domain.usecase.GetFavoriteVacanciesCountUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class BindAppUseCase {

    @Binds
    abstract fun bindGetFavoriteVacanciesCountUseCase(
        getFavoriteVacanciesCountUseCaseImpl: GetFavoriteVacanciesCountUseCaseImpl,
    ): GetFavoriteVacanciesCountUseCase
}
