package com.imysko.features.searchVacancy.di

import android.content.Context
import kotlin.properties.Delegates.notNull

interface SearchVacancyDeps {

    val appContext: Context
}

interface SearchVacancyDepsProvider {

    val deps: SearchVacancyDeps

    companion object : SearchVacancyDepsProvider by SearchVacancyDepsStore
}

object SearchVacancyDepsStore : SearchVacancyDepsProvider {

    override var deps: SearchVacancyDeps by notNull()
}