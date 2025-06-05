package com.example.words_ui

data class NewWordUiState(
    var word: String = "",
    var translation: String = "",
    var isInvalidWord: Boolean = false,
    var categories: MutableList<Long> = mutableListOf<Long>()
)
