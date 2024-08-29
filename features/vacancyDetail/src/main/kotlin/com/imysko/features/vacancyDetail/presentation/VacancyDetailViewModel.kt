package com.imysko.features.vacancyDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.features.vacancyDetail.domain.usecase.GetVacancyByIdUseCase
import com.imysko.features.vacancyDetail.presentation.mappers.mapToCompanyInfoAdapterModel
import com.imysko.features.vacancyDetail.presentation.mappers.mapToVacancyHeaderAdapterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VacancyDetailViewModel(
    private val id: String,
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(VacancyDetailUiState())
    val uiState: StateFlow<VacancyDetailUiState>
        get() = _uiState.asStateFlow()

    init {
        getVacancy()
    }

    private fun getVacancy() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getVacancyByIdUseCase(id)?.also { vacancy ->
                    _uiState.update {
                        it.copy(
                            vacancyHeader = vacancy.mapToVacancyHeaderAdapterModel(),
                            companyInfo = vacancy.mapToCompanyInfoAdapterModel(),
                            description = vacancy.description,
                            responsibilities = vacancy.responsibilities,
                        )
                    }
                }
            }
        }
    }
}
