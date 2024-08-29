package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class QuestionAdapterModel(
    val question: String,
) : DelegateAdapterItem {

    override fun id(): Any = QuestionAdapterModel::class.toString()

    override fun content(): Any = this
}
