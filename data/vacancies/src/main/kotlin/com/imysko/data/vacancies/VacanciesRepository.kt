package com.imysko.data.vacancies

import com.imysko.data.vacancies.entities.Vacancy
import kotlinx.coroutines.flow.StateFlow

interface VacanciesRepository {

    val vacanciesList: StateFlow<List<Vacancy>>

    suspend fun changeVacancyFavoriteState(id: String, newFavoriteState: Boolean)

    suspend fun getVacancyById(id: String): Vacancy?
}