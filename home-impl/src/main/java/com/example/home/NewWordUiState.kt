package com.example.home

data class NewWordUiState(
    var word: String = "",
    var translation: String = "",
    var isInvalidWord: Boolean = false
)
