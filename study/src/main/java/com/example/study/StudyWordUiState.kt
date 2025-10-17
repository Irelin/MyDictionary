package com.example.study

import com.example.study.presentation.models.StudyWordUI

open class StudyUiState(open val words: List<StudyWordUI>)

data class MemorizeUiState(override val words: List<StudyWordUI>): StudyUiState(words)

data class ExerciseUiState(override val words: List<StudyWordUI>, var learnedWordsCount: Int = 0): StudyUiState(words)
