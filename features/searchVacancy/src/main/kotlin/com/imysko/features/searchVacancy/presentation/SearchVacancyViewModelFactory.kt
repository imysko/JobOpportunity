package com.imysko.features.searchVacancy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.searchVacancy.domain.usecase.ChangeVacancyFavoriteStateUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
import javax.inject.Inject
import javax.inject.Provider

class SearchVacancyViewModelFactory @Inject constructor(
    private val getOffersListUseCase: Provider<GetOffersListUseCase>,
    private val getVacanciesListUseCase: Provider<GetVacanciesListUseCase>,
    private val changeVacancyFavoriteStateUseCase: Provider<ChangeVacancyFavoriteStateUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == SearchVacancyViewModel::class.java)
        return SearchVacancyViewModel(
            getOffersListUseCase.get(),
            getVacanciesListUseCase.get(),
            changeVacancyFavoriteStateUseCase.get(),
        ) as T
    }
}
