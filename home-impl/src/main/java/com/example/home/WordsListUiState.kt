package com.example.home

import com.example.home.presentation.models.WordUI

sealed interface WordsListUiState {
    data class Success(val words: List<WordUI>) : WordsListUiState
    data object Error : WordsListUiState
    data object Loading : WordsListUiState
}