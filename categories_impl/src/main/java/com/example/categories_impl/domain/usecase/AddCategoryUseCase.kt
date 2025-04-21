package com.example.categories_impl.domain.usecase

import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_impl.repository.CategoriesRepository
import javax.inject.Inject

class AddCategoryUseCase@Inject constructor(private val categoriesRepository: CategoriesRepository): AddCategory {
    override suspend fun invoke(name: String) {
        categoriesRepository.addNewCategory(name)
    }
}