package com.imysko.features.vacancyDetail.presentation.mappers

import com.imysko.data.vacancies.entities.Vacancy
import com.imysko.features.vacancyDetail.presentation.entities.CompanyInfoAdapterModel
import com.imysko.features.vacancyDetail.presentation.entities.VacancyHeaderAdapterModel

fun Vacancy.mapToVacancyHeaderAdapterModel() = VacancyHeaderAdapterModel(
    title = title,
    salary = salary.full,
    experience = experience.text,
    schedulesType = schedules.joinToString(", ").replaceFirstChar { it.uppercase() },
)

fun Vacancy.mapToCompanyInfoAdapterModel() = CompanyInfoAdapterModel(
    name = company,
    address = listOf(address.town, address.street, address.house).joinToString(", "),
)
