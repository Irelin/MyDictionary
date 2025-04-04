package com.example.words_impl.domain.usecase

import com.example.words_api.domain.usecase.AddWord
import com.example.words_impl.repository.WordsRepository
import javax.inject.Inject

class AddWordUseCase @Inject constructor(private val wordsRepository: WordsRepository): AddWord {
    override suspend operator fun invoke(word: String, translation: String) {
        wordsRepository.addNewWord(word, translation)
    }
}