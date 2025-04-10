package com.example.categories_impl.domain.usecase

import com.example.categories_api.domain.models.Category
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_impl.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) :
    GetAllCategories {
    override suspend fun invoke(): Flow<List<Category>> {
        return categoriesRepository.getAll()
    }
}