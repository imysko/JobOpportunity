package com.imysko.jobopportunity.domain.usecase

import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoriteVacanciesCountUseCase {

    operator fun invoke(): Flow<Int>
}

internal class GetFavoriteVacanciesCountUseCaseImpl @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
) : GetFavoriteVacanciesCountUseCase {

    override fun invoke(): Flow<Int> = vacanciesRepository.getFavoriteVacanciesCount()
}
