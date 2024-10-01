package com.imysko.data.vacancies.mock

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.imysko.common.core.utils.LocalDateJsonAdapter
import com.imysko.common.core.utils.readJsonFromAssets
import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import com.imysko.data.vacancies.local.dao.FavoriteVacancyDao
import com.imysko.data.vacancies.local.mapper.mapToFavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

internal class MockVacanciesRepository @Inject constructor(
    private val context: Context,
    private val favoriteVacancyDao: FavoriteVacancyDao,
) : VacanciesRepository {

    private val _vacanciesList: MutableStateFlow<List<Vacancy>> = MutableStateFlow(emptyList())

    private suspend fun loadData() {
        val gson = GsonBuilder().registerTypeAdapter(
            LocalDate::class.java,
            LocalDateJsonAdapter().nullSafe(),
        ).create()

        val jsonString = readJsonFromAssets(context, FILE_NAME)
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val vacanciesJsonArray = jsonObject.getAsJsonArray(VACANCIES_KEY)

        _vacanciesList.update {
            gson.fromJson<List<Vacancy>?>(
                vacanciesJsonArray,
                object : TypeToken<List<Vacancy>>() {}.type
            ).map {
                it.copy(isFavorite = favoriteVacancyDao.exist(it.id))
            }
        }
    }

    override suspend fun getVacanciesList(): Flow<List<Vacancy>> = _vacanciesList
        .onStart { loadData() }

    override suspend fun addVacancyToFavorite(newVacancy: Vacancy) {
        favoriteVacancyDao.upsert(newVacancy.mapToFavoriteVacancyEntity())

        _vacanciesList.update {
            it.map { vacancy ->
                return@map if (vacancy.id == newVacancy.id) {
                    vacancy.copy(isFavorite = true)
                } else {
                    vacancy
                }
            }
        }
    }

    override suspend fun deleteVacancyFromFavorite(id: String) {
        favoriteVacancyDao.delete(id)

        _vacanciesList.update {
            it.map { vacancy ->
                return@map if (vacancy.id == id) {
                    vacancy.copy(isFavorite = false)
                } else {
                    vacancy
                }
            }
        }
    }

    override fun getFavoriteVacanciesList(): Flow<List<Vacancy>> {
        return favoriteVacancyDao.getAll().map { list ->
            list.mapNotNull { getVacancyById(it.id) }
        }
    }

    override fun getFavoriteVacanciesCount(): Flow<Int> {
        return favoriteVacancyDao.count()
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        return _vacanciesList.value.firstOrNull { it.id == id }
    }

    companion object {

        const val FILE_NAME = "mock.json"

        const val VACANCIES_KEY = "vacancies"
    }
}
