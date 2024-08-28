package com.imysko.features.searchVacancy.presentation

import com.imysko.features.searchVacancy.presentation.entities.OfferAdapterModel
import com.imysko.features.searchVacancy.presentation.entities.VacancyAdapterModel

data class SearchVacancyUiState(
    val isMoreVacancyState: Boolean = false,
    val isShowOffersList: Boolean = true,
    val offersList: List<OfferAdapterModel> = emptyList(),
    val totalVacancies: Int = 0,
    val vacanciesList: List<VacancyAdapterModel> = emptyList(),
)
