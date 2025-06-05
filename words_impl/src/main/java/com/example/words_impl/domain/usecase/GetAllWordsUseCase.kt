package com.example.words_impl.domain.usecase

import com.example.words_api.domain.models.Word
import com.example.words_api.domain.usecase.GetAllWords
import com.example.words_impl.repository.WordsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllWordsUseCase @Inject constructor(private val wordsRepository: WordsRepository): GetAllWords {
    override suspend fun invoke(): Flow<List<Word>> = wordsRepository.getAll()
}