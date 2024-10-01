package com.imysko.data.offers.domain.entities

data class Offer(
    val id: OfferId?,
    val title: String,
    val button: OfferAction?,
    val link: String,
)
