package com.example.study.presentation.ui

import com.example.words_data.models.Word
import javax.inject.Inject

class StudyWordUiMapper @Inject constructor() {
    fun map(word: Word): StudyWordUI = word.let { StudyWordUI(WordUI(it.id, it.originValue, it.translatedValue)) }
}