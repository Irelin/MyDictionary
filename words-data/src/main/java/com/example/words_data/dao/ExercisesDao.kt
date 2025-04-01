package com.example.words_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.words_data.models.dbo.ExerciseDBO

@Dao
interface ExercisesDao {
    @Query("select * from exercises")
    suspend fun getAll(): List<ExerciseDBO>

    @Insert
    suspend fun insertAll(data: List<ExerciseDBO>)
}