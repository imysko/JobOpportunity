package com.imysko.features.favorite.domain.usecase

import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoritesVacanciesListUseCase {

    operator fun invoke(): Flow<List<Vacancy>>
}

internal class GetFavoritesVacanciesListUseCaseImpl @Inject constructor(
    private val favoriteVacanciesRepository: VacanciesRepository,
) : GetFavoritesVacanciesListUseCase {

    override fun invoke(): Flow<List<Vacancy>> =
        favoriteVacanciesRepository.getFavoriteVacanciesList()
}
