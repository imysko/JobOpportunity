package com.imysko.features.vacancyDetail.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class CompanyInfoAdapterModel(
    val name: String = "",
    val address: String = "",
) : DelegateAdapterItem {

    override fun id(): Any = name

    override fun content(): Any = this
}
