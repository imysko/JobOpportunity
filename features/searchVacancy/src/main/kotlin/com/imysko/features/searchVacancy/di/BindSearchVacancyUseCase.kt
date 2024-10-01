package com.imysko.features.searchVacancy.di

import com.imysko.features.searchVacancy.domain.usecase.AddVacancyToFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.AddVacancyToFavoriteUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.DeleteVacancyFromFavoriteUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCaseImpl
import com.imysko.features.searchVacancy.domain.usecase.GetVacancyByIdUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacancyByIdUseCaseImpl
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
    abstract fun bindGetVacancyByIdUseCase(
        getVacancyByIdUseCaseImpl: GetVacancyByIdUseCaseImpl,
    ): GetVacancyByIdUseCase

    @Binds
    abstract fun bindAddVacancyToFavoriteUseCase(
        addVacancyToFavoriteUseCaseImpl: AddVacancyToFavoriteUseCaseImpl,
    ): AddVacancyToFavoriteUseCase

    @Binds
    abstract fun bindDeleteVacancyFromFavoriteUseCase(
        deleteVacancyFromFavoriteUseCaseImpl: DeleteVacancyFromFavoriteUseCaseImpl,
    ): DeleteVacancyFromFavoriteUseCase
}
