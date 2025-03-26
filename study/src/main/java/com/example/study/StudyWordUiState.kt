package com.example.study

import com.example.study.presentation.ui.StudyWordUI

data class StudyWordUiState(val words: List<StudyWordUI>, var learnedWordsCount: Int = 0)
