package com.example.home

import com.example.home.presentation.models.CategoryUI
import com.example.home.presentation.models.WordUI

sealed interface WordsListUiState {
    data class Success(val words: List<WordUI>) : WordsListUiState
    data object Error : WordsListUiState
    data object Loading : WordsListUiState
}

sealed interface CategoriesListUiState {
    data class Success(val categories: List<CategoryUI>) : CategoriesListUiState
    data object Error : CategoriesListUiState
    data object Loading : CategoriesListUiState
}