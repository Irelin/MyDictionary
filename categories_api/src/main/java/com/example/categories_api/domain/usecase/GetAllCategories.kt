package com.example.categories_api.domain.usecase

import com.example.categories_api.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface GetAllCategories {
    suspend operator fun invoke(): Flow<List<Category>>
}