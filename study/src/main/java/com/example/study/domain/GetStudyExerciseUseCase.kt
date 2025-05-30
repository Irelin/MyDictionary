package com.example.study.domain

import com.example.words_impl.repository.WordsRepository
import javax.inject.Inject

class GetStudyExerciseUseCase @Inject constructor(private val wordsRepository: WordsRepository) {
    suspend operator fun invoke() = wordsRepository.getAll()
}