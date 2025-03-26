package com.example.study.presentation.ui

import com.example.words_data.models.Word

class StudyWordUiMapper {
    fun map(word: Word): StudyWordUI = word.let { StudyWordUI(WordUI(it.id, it.originValue, it.translatedValue)) }
}