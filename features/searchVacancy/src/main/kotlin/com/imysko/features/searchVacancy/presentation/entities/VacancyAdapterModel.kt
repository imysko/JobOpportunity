package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class VacancyAdapterModel(
    val id: String,
    val lookingNumber: Int?,
    val isFavorite: Boolean,
    val title: String,
    val salary: String?,
    val town: String,
    val companyName: String,
    val experience: String,
    val publishedDate: String,
) : DelegateAdapterItem {

    val isLookingNumberVisible: Boolean = lookingNumber != null
    val isSalaryVisible: Boolean = salary.isNullOrEmpty()

    override fun id(): Any = id

    override fun content(): Any = this
}
