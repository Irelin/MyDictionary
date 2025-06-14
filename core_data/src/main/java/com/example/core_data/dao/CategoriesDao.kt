package com.example.core_data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.core_data.dbo.CategoryDBO
import com.example.core_data.dbo.CategoryInfoDBO
import com.example.core_data.dbo.CategoryWithWordsDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {
    @Query("select * from categories")
    suspend fun getAll(): List<CategoryDBO>

    @Query("select * from categories")
    fun observeAll(): Flow<List<CategoryDBO>>

    @Query("select * from CategoryInfoDBO order by categoryId desc")
    fun observeAllCategoriesInfo(): Flow<List<CategoryInfoDBO>>

    @Query("select * from CategoryInfoDBO order by categoryId desc limit :count")
    fun observeLastCategoriesInfo(count: Int = 1): Flow<List<CategoryInfoDBO>>

    @Transaction
    @Query("SELECT * FROM categories where categoryId = :id")
    fun getCategoryWithWords(id: Long = 1): CategoryWithWordsDBO

    @Insert
    suspend fun insert(category: CategoryDBO): Long

    @Delete
    suspend fun remove(category: CategoryDBO)

    @Query("delete from categories")
    suspend fun clean()
}