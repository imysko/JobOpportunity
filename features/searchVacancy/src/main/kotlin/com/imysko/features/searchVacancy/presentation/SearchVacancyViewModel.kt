package com.imysko.features.searchVacancy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.features.searchVacancy.domain.usecase.AddVacancyToFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacancyByIdUseCase
import com.imysko.features.searchVacancy.presentation.mappers.mapToAdapterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchVacancyViewModel(
    private val getOffersListUseCase: GetOffersListUseCase,
    private val getVacanciesListUseCase: GetVacanciesListUseCase,
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    private val addVacancyToFavoriteUseCase: AddVacancyToFavoriteUseCase,
    private val deleteVacancyFromFavoriteUseCase: DeleteVacancyFromFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchVacancyUiState())
    val uiState: StateFlow<SearchVacancyUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getOffersListUseCase().map { it.mapToAdapterModel() }.also { offersList ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            offersList = offersList,
                        )
                    }
                }

                getVacanciesListUseCase().collectLatest { vacanciesList ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            totalVacancies = vacanciesList.size,
                            vacanciesList = vacanciesList.map { it.mapToAdapterModel() },
                        )
                    }
                }
            }
        }
    }

    fun showVacanciesByMatch() {
        _uiState.update { it.copy(screenState = SearchVacancyUiState.ScreenState.VacanciesByMatch) }
    }

    fun backToMainVacanciesState() {
        _uiState.update { it.copy(screenState = SearchVacancyUiState.ScreenState.MainState) }
    }

    fun addVacancyToFavorite(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getVacancyByIdUseCase(id)?.let {
                    addVacancyToFavoriteUseCase(it)
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
