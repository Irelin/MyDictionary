package com.example.study.presentation.ui

data class StudyWordUI(
    val word: WordUI,
    var isTranslationVisible: Boolean = false,
    var isAnswerCorrect: Boolean = false,
    var isAnswerWrong: Boolean = false
)

data class WordUI(val id: Long, val originValue: String, val translatedValue: String)
