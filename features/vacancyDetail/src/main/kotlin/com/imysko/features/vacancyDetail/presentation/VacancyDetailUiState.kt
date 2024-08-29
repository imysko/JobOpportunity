package com.imysko.features.vacancyDetail.presentation

import com.imysko.features.vacancyDetail.presentation.entities.CompanyInfoAdapterModel
import com.imysko.features.vacancyDetail.presentation.entities.VacancyHeaderAdapterModel

data class VacancyDetailUiState(
    val vacancyHeader: VacancyHeaderAdapterModel = VacancyHeaderAdapterModel(),
    val companyInfo: CompanyInfoAdapterModel = CompanyInfoAdapterModel(),
    val description: String? = null,
    val responsibilities: String? = null,
)
