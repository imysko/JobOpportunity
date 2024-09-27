package com.imysko.features.searchVacancy.presentation

import com.imysko.common.ui.vacancies.VacancyAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.OfferAdapterModel

data class SearchVacancyUiState(
    val screenState: ScreenState = ScreenState.MainState,
    val offersList: List<OfferAdapterModel> = emptyList(),
    val totalVacancies: Int = 0,
    val vacanciesList: List<VacancyAdapterModel> = emptyList(),
) {

    sealed interface ScreenState {

        data object MainState : ScreenState
        data object VacanciesByMatch : ScreenState
    }
}
