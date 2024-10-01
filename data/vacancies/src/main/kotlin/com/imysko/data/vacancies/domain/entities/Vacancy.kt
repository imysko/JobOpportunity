package com.imysko.data.vacancies.domain.entities

import java.time.LocalDate

data class Vacancy(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val company: String,
    val address: CompanyAddress,
    val experience: Experience,
    val publishedDate: LocalDate,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>,
)
