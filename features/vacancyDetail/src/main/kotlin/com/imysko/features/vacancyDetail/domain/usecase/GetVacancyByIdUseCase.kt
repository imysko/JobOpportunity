package com.imysko.features.vacancyDetail.domain.usecase

import com.imysko.data.vacancies.VacanciesRepository
import com.imysko.data.vacancies.entities.Vacancy
import javax.inject.Inject

interface GetVacancyByIdUseCase {

    suspend operator fun invoke(id: String): Vacancy?
}

internal class GetVacancyByIdUseCaseImpl @Inject constructor(
    private val vacancyRepository: VacanciesRepository,
) : GetVacancyByIdUseCase {

    override suspend fun invoke(id: String): Vacancy? {
        return vacancyRepository.getVacancyById(id)
    }
}
