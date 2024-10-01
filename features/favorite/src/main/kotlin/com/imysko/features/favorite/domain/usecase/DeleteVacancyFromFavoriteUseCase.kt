package com.imysko.features.favorite.domain.usecase

import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import javax.inject.Inject

interface DeleteVacancyFromFavoriteUseCase {

    suspend operator fun invoke(id: String)
}

internal class DeleteVacancyFromFavoriteUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : DeleteVacancyFromFavoriteUseCase {

    override suspend fun invoke(id: String) {
        vacanciesRepository.deleteVacancyFromFavorite(id)
    }
}
