package com.imysko.data.offers.domain.repository

import com.imysko.data.offers.domain.entities.Offer

interface OffersRepository {

    suspend fun getOffersList(): List<Offer>
}
