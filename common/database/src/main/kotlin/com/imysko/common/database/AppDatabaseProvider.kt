package com.imysko.common.database

import android.content.Context
import androidx.room.Room
import com.imysko.data.vacancies.local.dao.FavoriteVacancyDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppDatabaseProvider {

    @Provides
    @Named("AppDatabaseName")
    fun provideAppDatabaseName() = "job.db"

    @Provides
    @Singleton
    fun provideAppDatabase(
        context: Context,
        @Named("AppDatabaseName") databaseName: String
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = databaseName,
    ).build()

    @Provides
    fun provideFavoriteVacancyDao(
        appDatabase: AppDatabase,
    ): FavoriteVacancyDao = appDatabase.favoriteVacancyDao
}
