package com.imysko.features.vacancyDetail.di

import android.content.Context
import kotlin.properties.Delegates.notNull

interface VacancyDetailDeps {

    val appContext: Context
}

interface VacancyDetailDepsProvider {

    val deps: VacancyDetailDeps

    companion object : VacancyDetailDepsProvider by VacancyDetailDepsStore
}

object VacancyDetailDepsStore : VacancyDetailDepsProvider {

    override var deps: VacancyDetailDeps by notNull()
}
