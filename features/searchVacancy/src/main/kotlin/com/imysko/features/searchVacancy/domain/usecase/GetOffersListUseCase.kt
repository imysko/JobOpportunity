package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.offers.domain.entities.Offer
import com.imysko.data.offers.domain.repository.OffersRepository
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
