package com.example.core_data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.core_data.dbo.WordDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Query("select * from words WHERE wordId LIKE :wordId")
    suspend fun get(wordId: Long): WordDBO

    @Query("select * from words")
    suspend fun getAll(): List<WordDBO>

    /*@Query("select * from words WHERE category_id LIKE :categoryId")
    suspend fun getAll(categoryId: Long): List<WordDBO>*/

    @Query("select * from words")
    fun observeAll(): Flow<List<WordDBO>>

    @Query("select * from words order by wordId desc limit :count")
    fun observeLast(count: Int = 1): Flow<List<WordDBO>>

    /*@Query("select * from words WHERE category_id LIKE :categoryId")
    fun observeAll(categoryId: Long): Flow<List<CategoryDBO>>*/

    @Insert
    suspend fun insert(word: WordDBO): Long

    @Delete
    suspend fun remove(word: WordDBO)

    @Query("delete from words")
    suspend fun clean()
}