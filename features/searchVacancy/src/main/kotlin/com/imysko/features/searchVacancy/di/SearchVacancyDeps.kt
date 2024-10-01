package com.imysko.features.searchVacancy.di

import android.content.Context
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import kotlin.properties.Delegates.notNull

interface SearchVacancyDeps {

    val appContext: Context
    val vacanciesRepository: VacanciesRepository
}

interface SearchVacancyDepsProvider {

    val deps: SearchVacancyDeps

    companion object : SearchVacancyDepsProvider by SearchVacancyDepsStore
}

object SearchVacancyDepsStore : SearchVacancyDepsProvider {

    override var deps: SearchVacancyDeps by notNull()
}