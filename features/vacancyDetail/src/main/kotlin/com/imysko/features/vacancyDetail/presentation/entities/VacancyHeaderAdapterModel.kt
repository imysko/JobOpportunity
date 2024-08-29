package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class VacancyHeaderAdapterModel(
    val title: String = "",
    val salary: String = "",
    val experience: String = "",
    val schedulesType: String = "",
) : DelegateAdapterItem {

    override fun id(): Any = title

    override fun content(): Any = this
}
