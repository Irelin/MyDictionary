package com.example.core_data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.core_data.dbo.CategoryWithWordsDBO
import com.example.core_data.dbo.CategoryWordCrossRef
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

    @Transaction
    @Query("SELECT * FROM categories where categoryId = :id limit 1")
    fun getCategoryWords(id: Long = 1): Flow<CategoryWithWordsDBO>

    /*@Query("select * from words WHERE category_id LIKE :categoryId")
    fun observeAll(categoryId: Long): Flow<List<CategoryDBO>>*/

    @Insert
    suspend fun insert(word: WordDBO): Long

    @Transaction
    suspend fun insertWordWithCategories(word: WordDBO, categories: List<Long>): Long {
        val wordId = insert(word)
        categories.forEach { categoryId ->
            insertCategoryWordCrossRef(CategoryWordCrossRef(categoryId = categoryId, wordId = wordId))
        }
        return wordId
    }

    @Insert
    suspend fun insertCategoryWordCrossRef(crossRef: CategoryWordCrossRef)

    @Delete
    suspend fun remove(word: WordDBO)

    @Query("delete from words")
    suspend fun clean()
}