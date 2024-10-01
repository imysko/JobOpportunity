package com.imysko.features.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.favorite.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.favorite.domain.usecase.GetFavoritesVacanciesListUseCase
import javax.inject.Inject
import javax.inject.Provider

class FavoriteViewModelFactory @Inject constructor(
    private val getFavoritesVacanciesListUseCase: Provider<GetFavoritesVacanciesListUseCase>,
    private val deleteVacancyFromFavoriteUseCase: Provider<DeleteVacancyFromFavoriteUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == FavoriteVewModel::class.java)
        return FavoriteVewModel(
            getFavoritesVacanciesListUseCase = getFavoritesVacanciesListUseCase.get(),
            deleteVacancyFromFavoriteUseCase = deleteVacancyFromFavoriteUseCase.get(),
        ) as T
    }
}
