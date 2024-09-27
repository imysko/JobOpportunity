package com.imysko.features.searchVacancy.presentation.entities

import androidx.annotation.DrawableRes
import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class SearchBlockAdapterModel(
    @DrawableRes val startIconDrawableRes: Int,
    val startIconAction: (() -> Unit)? = null,
    val hint: String,
) : DelegateAdapterItem {

    override fun id(): Any = SearchBlockAdapterModel::class.toString()

    override fun content(): Any = this
}
