package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.vacancies.VacanciesRepository
import javax.inject.Inject

interface ChangeVacancyFavoriteStateUseCase {

    suspend operator fun invoke(id: String, favoriteState: Boolean)
}

internal class ChangeVacancyFavoriteStateUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : ChangeVacancyFavoriteStateUseCase {

    override suspend fun invoke(id: String, favoriteState: Boolean) {
        return vacanciesRepository.changeVacancyFavoriteState(id, favoriteState)
    }
}
