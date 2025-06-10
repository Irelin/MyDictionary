package com.example.study_api.domain.usecase

import com.example.words_api.domain.models.Word
import kotlinx.coroutines.flow.Flow

interface GetStudyExercise {
    suspend operator fun invoke(): Flow<List<Word>>
}