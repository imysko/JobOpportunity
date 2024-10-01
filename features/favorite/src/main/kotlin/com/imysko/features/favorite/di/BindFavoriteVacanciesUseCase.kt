package com.imysko.features.favorite.di

import com.imysko.features.favorite.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.favorite.domain.usecase.DeleteVacancyFromFavoriteUseCaseImpl
import com.imysko.features.favorite.domain.usecase.GetFavoritesVacanciesListUseCase
import com.imysko.features.favorite.domain.usecase.GetFavoritesVacanciesListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class BindFavoriteVacanciesUseCase {

    @Binds
    abstract fun bindGetFavoriteVacanciesListUseCase(
        getFavoritesVacanciesListUseCaseImpl: GetFavoritesVacanciesListUseCaseImpl,
    ): GetFavoritesVacanciesListUseCase

    @Binds
    abstract fun bindDeleteVacancyFromFavoriteUseCase(
        deleteVacancyFromFavoriteUseCaseImpl: DeleteVacancyFromFavoriteUseCaseImpl,
    ): DeleteVacancyFromFavoriteUseCase
}
