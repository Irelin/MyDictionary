package com.example.study.presentation.mapper

import com.example.study.presentation.models.StudyWordUI
import com.example.study.presentation.models.WordUI
import com.example.words_api.domain.models.Word
import javax.inject.Inject

class StudyWordUiMapper @Inject constructor() {
    fun map(word: Word): StudyWordUI = word.let {
        StudyWordUI(
            WordUI(
                it.id,
                it.originValue,
                it.translatedValue
            )
        )
    }
}