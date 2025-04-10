package com.example.categories_api.domain.usecase

import com.example.categories_api.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface GetLastCategories {
    suspend operator fun invoke(count: Int): Flow<List<Category>>
}