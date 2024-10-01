package com.imysko.features.searchVacancy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.searchVacancy.domain.usecase.AddVacancyToFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.DeleteVacancyFromFavoriteUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetOffersListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacanciesListUseCase
import com.imysko.features.searchVacancy.domain.usecase.GetVacancyByIdUseCase
import javax.inject.Inject
import javax.inject.Provider

class SearchVacancyViewModelFactory @Inject constructor(
    private val getOffersListUseCase: Provider<GetOffersListUseCase>,
    private val getVacanciesListUseCase: Provider<GetVacanciesListUseCase>,
    private val getVacancyByIdUseCase: Provider<GetVacancyByIdUseCase>,
    private val addVacancyToFavoriteUseCase: Provider<AddVacancyToFavoriteUseCase>,
    private val deleteVacancyFromFavoriteUseCase: Provider<DeleteVacancyFromFavoriteUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == SearchVacancyViewModel::class.java)
        return SearchVacancyViewModel(
            getOffersListUseCase = getOffersListUseCase.get(),
            getVacanciesListUseCase = getVacanciesListUseCase.get(),
            getVacancyByIdUseCase = getVacancyByIdUseCase.get(),
            addVacancyToFavoriteUseCase = addVacancyToFavoriteUseCase.get(),
            deleteVacancyFromFavoriteUseCase = deleteVacancyFromFavoriteUseCase.get(),
        ) as T
    }
}
