package com.imysko.features.searchVacancy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.data.offers.entities.Offer
import com.imysko.features.searchVacancy.domain.usecase.ChangeVacancyFavoriteStateUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
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
    private val changeVacancyFavoriteStateUseCase: ChangeVacancyFavoriteStateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchVacancyUiState())
    val uiState: StateFlow<SearchVacancyUiState>
        get() = _uiState.asStateFlow()

    private val _offersList = MutableStateFlow<List<Offer>>(listOf())
    val offersList: StateFlow<List<Offer>>
        get() = _offersList.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getOffersListUseCase().map { it.mapToAdapterModel() }.also { offersList ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            isShowOffersList = offersList.any(),
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

    fun changeVacancyFavoriteState(id: String, newFavoriteState: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                changeVacancyFavoriteStateUseCase(id, newFavoriteState)
            }
        }
    }
}
