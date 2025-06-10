package com.example.study_impl.domain.usecase

import com.example.study_api.domain.usecase.GetStudyExercise

class GetStudyExerciseUseCase(/*private val wordsRepository: WordsRepository*/): GetStudyExercise {
    override suspend operator fun invoke() =  throw NotImplementedError("wordsRepository.getAll()")
}