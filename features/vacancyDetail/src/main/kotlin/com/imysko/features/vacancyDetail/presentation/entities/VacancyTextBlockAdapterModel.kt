package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class VacancyTextBlockAdapterModel(
    val title: String? = null,
    val text: String,
) : DelegateAdapterItem {

    override fun id(): Any = text

    override fun content(): Any = content()
}
