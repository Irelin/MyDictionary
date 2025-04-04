package com.example.words_impl.domain.usecase

import com.example.words_api.domain.usecase.GetLastWords
import com.example.words_impl.repository.WordsRepository
import javax.inject.Inject

class GetLastWordsUseCase @Inject constructor(private val wordsRepository: WordsRepository) :
    GetLastWords {
    override suspend operator fun invoke(count: Int) = wordsRepository.getLast(count)
}