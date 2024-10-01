package com.imysko.data.vacancies.di

import android.content.Context
import com.imysko.data.vacancies.domain.repository.VacanciesRepository
import com.imysko.data.vacancies.local.dao.FavoriteVacancyDao
import com.imysko.data.vacancies.mock.MockVacanciesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object VacanciesRepositoryProvider {

    @Singleton
    @Provides
    fun providerMockVacanciesRepository(
        context: Context,
        favoriteVacancyDao: FavoriteVacancyDao,
    ): VacanciesRepository = MockVacanciesRepository(context, favoriteVacancyDao)
}
