package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.common.ui.delegateAdapter.DelegateAdapterItem

data class OffersBlockAdapterModel(
    val offersList: List<OfferAdapterModel>,
) : DelegateAdapterItem {

    override fun id(): Any = offersList.size

    override fun content(): Any = offersList
}
