package com.example.words_api.domain.usecase

import com.example.words_api.domain.models.Word
import kotlinx.coroutines.flow.Flow

interface GetLastWords {
    suspend operator fun invoke(count: Int): Flow<List<Word>>
}