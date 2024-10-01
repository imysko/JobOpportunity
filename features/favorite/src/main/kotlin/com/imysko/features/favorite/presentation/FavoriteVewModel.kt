package com.imysko.features.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.features.favorite.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.favorite.domain.usecase.GetFavoritesVacanciesListUseCase
import com.imysko.features.favorite.presentation.mappers.mapToAdapterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteVewModel(
    private val getFavoritesVacanciesListUseCase: GetFavoritesVacanciesListUseCase,
    private val deleteVacancyFromFavoriteUseCase: DeleteVacancyFromFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState>
        get() = _uiState.asStateFlow()

    init {
        getFavoriteVacanciesList()
    }

    private fun getFavoriteVacanciesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getFavoritesVacanciesListUseCase().collectLatest { list ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            favoriteVacanciesCount = list.count(),
                            favoriteVacancies = list.map { it.mapToAdapterModel() },
                        )
                    }
                }
            }
        }
    }

    fun deleteVacancyFromFavorite(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteVacancyFromFavoriteUseCase(id)
            }
        }
    }
}
