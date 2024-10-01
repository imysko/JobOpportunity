package com.imysko.features.searchVacancy.presentation.entities

import com.imysko.data.offers.domain.entities.OfferId

data class OfferAdapterModel(
    val id: OfferId?,
    val title: String,
    val action: String?,
    val link: String,
)
