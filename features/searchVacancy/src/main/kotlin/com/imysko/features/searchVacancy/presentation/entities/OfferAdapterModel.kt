package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.data.offers.entities.OfferId

data class OfferAdapterModel(
    val id: OfferId?,
    val title: String,
    val action: String?,
    val link: String,
)
