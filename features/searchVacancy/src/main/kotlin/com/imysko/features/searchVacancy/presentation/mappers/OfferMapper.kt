package com.imysko.features.searchVacancy.presentation.mappers

import com.imysko.data.offers.domain.entities.Offer
import com.imysko.features.searchVacancy.presentation.entities.OfferAdapterModel

fun Offer.mapToAdapterModel(): OfferAdapterModel = OfferAdapterModel(
    id = id,
    title = title,
    action = button?.text,
    link = link,
)
