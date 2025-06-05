package com.example.words_ui

import com.example.words_ui.models.WordUI

sealed interface WordsListUiState {
    data class Success(val words: List<WordUI>) : WordsListUiState
    data object Error : WordsListUiState
    data object Loading : WordsListUiState
}