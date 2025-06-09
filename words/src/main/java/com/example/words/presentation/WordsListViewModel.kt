package com.example.words.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words_api.domain.usecase.GetCategoryWords
import com.example.words_ui.WordsListUiState
import com.example.words_ui.mapper.WordUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordsListViewModel @Inject constructor(
    private val getCategoryWords: GetCategoryWords,
    private val wordUiMapper: WordUiMapper,
    private val categoryId: Long
) : ViewModel() {
    private val _wordsListUiState: MutableStateFlow<WordsListUiState> =
        MutableStateFlow(WordsListUiState.Loading)
    val wordsListUiState: StateFlow<WordsListUiState> = _wordsListUiState.asStateFlow()

    init {
        getWords()
    }

    private fun getWords() {
        viewModelScope.launch {
            _wordsListUiState.value = WordsListUiState.Loading
            getCategoryWords(categoryId).collect { category ->
                _wordsListUiState.value =
                    WordsListUiState.Success(category.words.map { wordUiMapper.map(it) }, category.name)
            }
        }
    }
}