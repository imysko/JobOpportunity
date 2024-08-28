package com.imysko.features.searchVacancy.di

import com.imysko.features.searchVacancy.domain.usecase.ChangeVacancyFavoriteStateUseCase
import com.imysko.features.searchVacancy.domain.usecase.ChangeVacancyFavoriteStateUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class BindSearchVacancyUseCase {

    @Binds
    abstract fun bindGetOffersListUseCase(
        getOffersListUseCaseImpl: GetOffersListUseCaseImpl,
    ): GetOffersListUseCase

    @Binds
    abstract fun bindGetVacanciesListUseCase(
        getVacanciesListUseCaseImpl: GetVacanciesListUseCaseImpl,
    ): GetVacanciesListUseCase

    @Binds
    abstract fun bindChangeVacancyFavoriteStateUseCase(
        changeVacancyFavoriteStateImpl: ChangeVacancyFavoriteStateUseCaseImpl,
    ): ChangeVacancyFavoriteStateUseCase
}
