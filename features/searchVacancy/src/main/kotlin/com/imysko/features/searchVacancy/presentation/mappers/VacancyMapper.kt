package com.imysko.features.searchVacancy.presentation.mappers

import com.imysko.common.ui.vacancies.VacancyAdapterModel
import com.imysko.data.vacancies.entities.Vacancy
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Vacancy.mapToAdapterModel() = VacancyAdapterModel(
    id = id,
    lookingNumber = lookingNumber,
    isFavorite = isFavorite,
    title = title,
    salary = salary.short,
    town = address.town,
    companyName = company,
    experience = experience.previewText,
    publishedDate = DateTimeFormatter.ofPattern("d MMMM", Locale("ru", "RU"))
        .format(publishedDate).lowercase(),
)
