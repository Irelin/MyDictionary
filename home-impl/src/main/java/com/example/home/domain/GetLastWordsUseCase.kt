package com.example.home.domain

import com.example.words_data.repository.WordsRepository
import javax.inject.Inject

class GetLastWordsUseCase @Inject constructor(private val wordsRepository: WordsRepository){
    suspend operator fun invoke(count: Int) = wordsRepository.getLast(count)
}