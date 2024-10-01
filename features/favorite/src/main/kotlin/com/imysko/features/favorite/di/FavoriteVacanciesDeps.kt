package com.imysko.features.favorite.di

import android.content.Context
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import kotlin.properties.Delegates.notNull

interface FavoriteVacanciesDeps {

    val appContext: Context
    val vacanciesRepository: VacanciesRepository
}

interface FavoriteVacanciesDepsProvider {

    val deps: FavoriteVacanciesDeps

    companion object : FavoriteVacanciesDepsProvider by FavoriteVacanciesDepsStore
}

object FavoriteVacanciesDepsStore : FavoriteVacanciesDepsProvider {

    override var deps: FavoriteVacanciesDeps by notNull()
}
