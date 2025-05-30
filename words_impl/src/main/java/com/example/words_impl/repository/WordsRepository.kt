package com.example.words_impl.repository

import com.example.core_data.dao.WordsDao
import com.example.core_data.dbo.WordDBO
import com.example.words_api.domain.models.Word
import com.example.words_impl.mapper.WordDBOToDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordsRepository @Inject constructor(
    private val wordsDao: WordsDao,
    private val wordMapper: WordDBOToDomainMapper
    //private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addNewWord(word: String, translation: String): Long {
        return wordsDao.insert(WordDBO(0L, word, translation))
    }

    suspend fun addNewWordWithCategories(word: String, translation: String, categories: List<Long>): Long {
        return wordsDao.insertWordWithCategories(WordDBO(0L, word, translation), categories)
    }

    suspend fun getAll(): Flow<List<Word>> {
        /*return withContext(ioDispatcher) {
            db.wordsDao().observeAll()
        }*/
        return wordsDao.observeAll().map { it.map { word -> wordMapper.map(word) } }
            .catch { listOf<Word>() }
    }

    suspend fun getLast(count: Int): Flow<List<Word>> {
        /*return withContext(ioDispatcher) {
            db.wordsDao().observeAll()
        }*/
        return wordsDao.observeLast(count).map { it.map { word -> wordMapper.map(word) } }
            .catch { listOf<Word>() }
    }
}