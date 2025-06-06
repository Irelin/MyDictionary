package com.example.words_api.domain.usecase

import com.example.words_api.domain.models.CategoryWords
import kotlinx.coroutines.flow.Flow

interface GetCategoryWords {
    suspend operator fun invoke(categoryId: Long): Flow<CategoryWords>
}