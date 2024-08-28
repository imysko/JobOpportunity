package com.imysko.data.offers

import com.imysko.data.offers.entities.Offer

interface OffersRepository {

    suspend fun getOffersList(): List<Offer>
}
