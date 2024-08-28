package com.imysko.data.vacancies

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.imysko.common.core.utils.LocalDateJsonAdapter
import com.imysko.common.core.utils.readJsonFromAssets
import com.imysko.data.vacancies.entities.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

internal class MockVacanciesRepository @Inject constructor(
    context: Context,
) : VacanciesRepository {

    private val _vacanciesList: MutableStateFlow<List<Vacancy>> = MutableStateFlow(emptyList())
    override val vacanciesList: StateFlow<List<Vacancy>>
        get() = _vacanciesList.asStateFlow()

    init {
        val gson = GsonBuilder().registerTypeAdapter(
            LocalDate::class.java,
            LocalDateJsonAdapter().nullSafe(),
        ).create()

        val jsonString = readJsonFromAssets(context, FILE_NAME)
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val vacanciesJsonArray = jsonObject.getAsJsonArray(VACANCIES_KEY)

        _vacanciesList.update {
            gson.fromJson(
                vacanciesJsonArray,
                object : TypeToken<List<Vacancy>>() {}.type
            )
        }
    }

    override suspend fun changeVacancyFavoriteState(id: String, newFavoriteState: Boolean) {
        _vacanciesList.update {
            it.map { vacancy ->
                return@map if (vacancy.id == id) {
                    vacancy.copy(isFavorite = newFavoriteState)
                } else {
                    vacancy
                }
            }
        }
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        return _vacanciesList.value.firstOrNull { it.id == id }
    }

    companion object {

        const val FILE_NAME = "mock.json"

        const val VACANCIES_KEY = "vacancies"
    }
}
