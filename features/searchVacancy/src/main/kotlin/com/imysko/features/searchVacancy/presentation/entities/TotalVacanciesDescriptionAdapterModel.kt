package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class TotalVacanciesDescriptionAdapterModel(
    val totalVacanciesCount: Int,
    val sortType: String,
) : DelegateAdapterItem {

    override fun id(): Any = TotalVacanciesDescriptionAdapterModel::class.toString()

    override fun content(): Any = this
}
