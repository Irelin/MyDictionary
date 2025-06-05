package com.example.home.presentation.mapper

import com.example.categories_api.domain.models.Category
import com.example.categories_ui.models.CategoryUI
import javax.inject.Inject

class CategoryUiMapper @Inject constructor() {
    fun map(category: Category): CategoryUI = category.let { CategoryUI(it.id, it.name, it.wordsCount) }
}