package com.imysko.data.vacancies.local.mapper

import com.imysko.data.vacancies.domain.entities.Vacancy
import com.imysko.data.vacancies.local.entities.FavoriteVacancyEntity

fun Vacancy.mapToFavoriteVacancyEntity(): FavoriteVacancyEntity = FavoriteVacancyEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    company = this.company,
)
