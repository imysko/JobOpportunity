package com.imysko.data.vacancies.di

import android.content.Context
import com.imysko.data.vacancies.MockVacanciesRepository
import com.imysko.data.vacancies.VacanciesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object VacanciesRepositoryProvider {

    @Singleton
    @Provides
    fun providerMockVacanciesRepository(
        context: Context
    ): VacanciesRepository = MockVacanciesRepository(context)
}
