package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class VacancyStatAdapterModel(
    val appliedNumber: Int?,
    val lookingNumber: Int?,
) : DelegateAdapterItem {

    override fun id(): Any = VacancyHeaderAdapterModel::class.toString()

    override fun content(): Any = this
}
