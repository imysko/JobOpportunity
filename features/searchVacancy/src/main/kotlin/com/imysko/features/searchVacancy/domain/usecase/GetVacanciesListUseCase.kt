package com.imysko.features.searchVacancy.domain.usecase

import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetVacanciesListUseCase {

    suspend operator fun invoke(): Flow<List<Vacancy>>
}

internal class GetVacanciesListUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : GetVacanciesListUseCase {

    override suspend fun invoke(): Flow<List<Vacancy>> {
        return vacanciesRepository.getVacanciesList()
    }
}
