package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.offers.OffersRepository
import com.imysko.data.offers.entities.Offer
import javax.inject.Inject

interface GetOffersListUseCase {

    suspend operator fun invoke(): List<Offer>
}

internal class GetOffersListUseCaseImpl @Inject constructor(
    private val offerRepository: OffersRepository,
) : GetOffersListUseCase {

    override suspend fun invoke(): List<Offer> {
        return offerRepository.getOffersList()
    }
}
