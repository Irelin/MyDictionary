package com.example.words_api.domain.usecase

import com.example.words_api.domain.models.Word
import kotlinx.coroutines.flow.Flow

interface GetAllWords {
    suspend operator fun invoke(): Flow<List<Word>>
}