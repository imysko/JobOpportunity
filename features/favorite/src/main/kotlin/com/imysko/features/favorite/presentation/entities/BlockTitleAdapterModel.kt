package com.imysko.features.favorite.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class BlockTitleAdapterModel(
    val title: String,
    val count: Int,
) : DelegateAdapterItem {

    override fun id(): Any = BlockTitleAdapterModel::class.toString()

    override fun content(): Any = this
}
