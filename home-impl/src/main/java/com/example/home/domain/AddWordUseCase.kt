package com.example.home.domain

import com.example.home_api.usecase.AddWord
import com.example.words_data.repository.WordsRepository
import javax.inject.Inject

class AddWordUseCase @Inject constructor(private val wordsRepository: WordsRepository): AddWord {
    override suspend operator fun invoke(word: String, translation: String) {
        wordsRepository.addNewWord(word, translation)
    }
}