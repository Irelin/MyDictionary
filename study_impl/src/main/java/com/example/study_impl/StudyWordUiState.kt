package com.example.study_impl

import com.example.study_impl.presentation.models.StudyWordUI

open class StudyUiState(open val words: List<StudyWordUI>)

data class MemorizeUiState(override val words: List<StudyWordUI>): StudyUiState(words)

data class ExerciseUiState(override val words: List<StudyWordUI>, var learnedWordsCount: Int = 0): StudyUiState(words)
