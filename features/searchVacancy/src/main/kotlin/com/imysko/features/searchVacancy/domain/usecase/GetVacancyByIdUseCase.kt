package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import javax.inject.Inject

interface GetVacancyByIdUseCase {

    suspend operator fun invoke(id: String): Vacancy?
}

internal class GetVacancyByIdUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : GetVacancyByIdUseCase {

    override suspend fun invoke(id: String): Vacancy? {
        return vacanciesRepository.getVacancyById(id)
    }
}
