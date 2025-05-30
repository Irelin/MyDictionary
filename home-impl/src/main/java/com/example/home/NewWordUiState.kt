package com.example.home

data class NewWordUiState(
    var word: String = "",
    var translation: String = "",
    var isInvalidWord: Boolean = false,
    var categories: MutableList<Long> = mutableListOf<Long>()
)

data class NewCategoryUiState(
    var name: String = ""
)
