package com.example.study.presentation.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class StudyWordUI(
    val word: WordUI,
    var isTranslationInitVisible: Boolean = false,
    var isAnswerCorrect: Boolean = false,
    var isAnswerWrong: Boolean = false
) {
    var translationVisible by mutableStateOf(isTranslationInitVisible)
}

data class WordUI(val id: Long, val originValue: String, val translatedValue: String)
