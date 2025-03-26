package com.example.study.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study.StudyWordUiState
import com.example.study.domain.GetStudyExerciseUseCase
import com.example.study.presentation.ui.StudyWordUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(private val getStudyExercise: GetStudyExerciseUseCase,
                                         private val studyWordUiMapper: StudyWordUiMapper) :
    ViewModel() {
    private val _wordsListUiState: MutableStateFlow<StudyWordUiState> =
        MutableStateFlow(StudyWordUiState(emptyList()))
    val wordsListUiState: StateFlow<StudyWordUiState> = _wordsListUiState.asStateFlow()

    init {
        getWords()
    }

    private fun getWords() {
        viewModelScope.launch {
            getStudyExercise().collect { items ->
                _wordsListUiState.value = StudyWordUiState(items.map { studyWordUiMapper.map(it) })
            }
        }
    }

    fun showTranslation(id: Long) {
        val words = _wordsListUiState.value.words
        _wordsListUiState.value = _wordsListUiState.value.copy(words = words.map {
            if (it.word.id == id) {
                it.copy(isTranslationVisible = true)
            } else {
                it
            }
        })
    }

    fun setAnswerCorrect(id: Long, isCorrect: Boolean) {
        val words = _wordsListUiState.value.words
        _wordsListUiState.value = _wordsListUiState.value.copy(words = words.map {
            if (it.word.id == id) {
                it.copy(isAnswerCorrect = isCorrect, isAnswerWrong = !isCorrect)
            } else {
                it
            }
        })
        //_wordsListUiState.value.learnedWordsCount++
    }
}