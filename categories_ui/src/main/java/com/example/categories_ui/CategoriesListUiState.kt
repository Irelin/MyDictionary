package com.example.categories_ui

import com.example.categories_ui.models.CategoryUI

sealed interface CategoriesListUiState {
    data class Success(val categories: List<CategoryUI>) : CategoriesListUiState
    data object Error : CategoriesListUiState
    data object Loading : CategoriesListUiState
}