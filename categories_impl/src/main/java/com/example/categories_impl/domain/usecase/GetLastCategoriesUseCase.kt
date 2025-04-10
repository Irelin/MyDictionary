package com.example.categories_impl.domain.usecase

import com.example.categories_api.domain.models.Category
import com.example.categories_api.domain.usecase.GetLastCategories
import com.example.categories_impl.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastCategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository): GetLastCategories {
    override suspend fun invoke(count: Int): Flow<List<Category>> {
        return categoriesRepository.getLast(count)
    }
}