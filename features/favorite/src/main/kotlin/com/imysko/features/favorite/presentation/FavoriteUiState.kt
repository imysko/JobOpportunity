package com.imysko.features.favorite.presentation

import com.imysko.common.ui.vacancies.VacancyAdapterModel

data class FavoriteUiState(
    val favoriteVacanciesCount: Int = 0,
    val favoriteVacancies: List<VacancyAdapterModel> = emptyList(),
)
