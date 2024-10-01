package com.imysko.data.vacancies.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_vacancy",
)
data class FavoriteVacancyEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("company")
    val company: String,
    @ColumnInfo("addition_time")
    val timestamp: Long = System.currentTimeMillis(),
)
