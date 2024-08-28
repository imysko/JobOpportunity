package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class BlockTitleAdapterModel(
    val title: String,
) : DelegateAdapterItem {

    override fun id(): Any = BlockTitleAdapterModel::class.toString()

    override fun content(): Any = title
}
