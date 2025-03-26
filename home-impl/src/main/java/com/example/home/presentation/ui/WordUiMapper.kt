package com.example.home.presentation.ui

import com.example.words_data.models.Word

class WordUiMapper {
    fun map(word: Word): WordUI = word.let { WordUI(it.id, it.originValue, it.translatedValue) }
}