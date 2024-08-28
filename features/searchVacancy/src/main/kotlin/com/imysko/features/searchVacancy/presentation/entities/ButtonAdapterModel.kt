package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class ButtonAdapterModel(
    val text: String,
) : DelegateAdapterItem {

    override fun id(): Any = ButtonAdapterModel::class.toString()

    override fun content(): Any = text
}
