package com.example.study.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.StudyUiState
import com.example.study.presentation.mapper.StudyWordUiMapper
import com.example.words_api.domain.usecase.GetLastWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudyViewModel @Inject constructor(
    private val getStudyExercise: GetLastWords, // todo: GetStudyExerciseUseCase
    private val studyWordUiMapper: StudyWordUiMapper
) :
    ViewModel() {
    private val _wordsListUiState: MutableStateFlow<StudyUiState> =
        MutableStateFlow(StudyUiState(emptyList()))
    val wordsListUiState: StateFlow<StudyUiState> = _wordsListUiState.asStateFlow()

    init {
        getWords()
    }

    private fun getWords() {
        viewModelScope.launch {
            getStudyExercise(4).collect { items ->
                _wordsListUiState.value = StudyUiState(items.map { studyWordUiMapper.map(it) })
            }
        }
    }

    fun showTranslation(id: Long) {
        _wordsListUiState.value.words.find { it.word.id == id }
            ?.let { it.translationVisible = !it.translationVisible }
    }

    fun setAnswerCorrect(id: Long, isCorrect: Boolean) {
        /*val words = _wordsListUiState.value.words
        _wordsListUiState.value = _wordsListUiState.value.copy(words = words.map {
            if (it.word.id == id) {
                it.copy(isAnswerCorrect = isCorrect, isAnswerWrong = !isCorrect)
            } else {
                it
            }
        })*/
    }
}