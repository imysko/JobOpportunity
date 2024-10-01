package com.imysko.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imysko.data.vacancies.local.dao.FavoriteVacancyDao
import com.imysko.data.vacancies.local.entities.FavoriteVacancyEntity

@Database(
    entities = [
        FavoriteVacancyEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract val favoriteVacancyDao: FavoriteVacancyDao
}
