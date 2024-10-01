package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import javax.inject.Inject

interface AddVacancyToFavoriteUseCase {

    suspend operator fun invoke(vacancy: Vacancy)
}

internal class AddVacancyToFavoriteUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : AddVacancyToFavoriteUseCase {

    override suspend fun invoke(vacancy: Vacancy) {
        vacanciesRepository.addVacancyToFavorite(vacancy)
    }
}
