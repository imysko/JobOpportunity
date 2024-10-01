package com.imysko.data.vacancies.domain.repository

import com.imysko.data.vacancies.domain.entities.Vacancy
import kotlinx.coroutines.flow.Flow

interface VacanciesRepository {

    suspend fun getVacanciesList(): Flow<List<Vacancy>>

    suspend fun getVacancyById(id: String): Vacancy?

    suspend fun addVacancyToFavorite(newVacancy: Vacancy)

    suspend fun deleteVacancyFromFavorite(id: String)

    fun getFavoriteVacanciesList(): Flow<List<Vacancy>>

    fun getFavoriteVacanciesCount(): Flow<Int>
}
