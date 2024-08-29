package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class QuestionsHeaderAdapterModel(
    val title: String,
    val description: String,
) : DelegateAdapterItem {

    override fun id(): Any = QuestionsHeaderAdapterModel::class.toString()

    override fun content(): Any = this
}
