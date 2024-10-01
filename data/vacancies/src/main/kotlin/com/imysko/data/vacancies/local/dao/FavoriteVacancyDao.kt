package com.imysko.data.vacancies.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.imysko.data.vacancies.local.entities.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteVacancyDao {

    @Upsert
    suspend fun upsert(vararg favoriteVacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM favorite_vacancy WHERE id = :vacancyId")
    suspend fun delete(vacancyId: String)

    @Query("SELECT * FROM favorite_vacancy ORDER BY addition_time DESC")
    fun getAll(): Flow<List<FavoriteVacancyEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_vacancy WHERE id = :vacancyId)")
    suspend fun exist(vacancyId: String): Boolean

    @Query("SELECT count(*) FROM favorite_vacancy")
    fun count(): Flow<Int>
}
