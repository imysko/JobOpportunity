package com.imysko.features.vacancyDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imysko.features.vacancyDetail.domain.usecase.GetVacancyByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Provider

class VacancyDetailViewModelFactory @AssistedInject constructor(
    @Assisted("vacancyId") private val id: String,
    private val getVacancyByIdUseCase: Provider<GetVacancyByIdUseCase>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == VacancyDetailViewModel::class.java)
        return VacancyDetailViewModel(id, getVacancyByIdUseCase.get()) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("vacancyId") id: String): VacancyDetailViewModelFactory
    }
}
