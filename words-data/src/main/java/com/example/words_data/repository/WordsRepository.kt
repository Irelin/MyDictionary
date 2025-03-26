package com.example.words_data.repository

import com.example.words_data.datasource.WordsDatabase
import com.example.words_data.mapper.WordDBOToDomainMapper
import com.example.words_data.models.Word
import com.example.words_data.models.dbo.WordDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class WordsRepository @Inject constructor(
    private val db: WordsDatabase,
    private val wordMapper: WordDBOToDomainMapper
    //private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun addNewWord(word: String, translation: String): Long {
        return db.wordsDao().insert(WordDBO(0L, word, translation))
    }

    suspend fun getAll(): Flow<List<Word>> {
        /*return withContext(ioDispatcher) {
            db.wordsDao().observeAll()
        }*/
        return db.wordsDao().observeAll().map { it.map { word -> wordMapper.map(word) } }
            .catch { listOf<Word>() }
    }

    suspend fun getLast(count: Int): Flow<List<Word>> {
        /*return withContext(ioDispatcher) {
            db.wordsDao().observeAll()
        }*/
        return db.wordsDao().observeLast(count).map { it.map { word -> wordMapper.map(word) } }
            .catch { listOf<Word>() }
    }
}