package com.example.home.presentation.ui

import com.example.words_api.domain.models.Word
import javax.inject.Inject

class WordUiMapper @Inject constructor() {
    fun map(word: Word): WordUI = word.let { WordUI(it.id, it.originValue, it.translatedValue) }
}