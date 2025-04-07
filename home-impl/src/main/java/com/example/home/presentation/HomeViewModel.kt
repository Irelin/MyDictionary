package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.NewWordUiState
import com.example.home.WordsListUiState
import com.example.home.presentation.ui.WordUiMapper
import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetLastWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
   private val getLastWords: GetLastWords,
   private val addNewWord: AddWord,
   private val wordUiMapper: WordUiMapper,
) : ViewModel() {
    private val _newWordUiState = MutableStateFlow(NewWordUiState())
    val newWordUiState: StateFlow<NewWordUiState> = _newWordUiState.asStateFlow()

    private val _wordsListUiState: MutableStateFlow<WordsListUiState> =
        MutableStateFlow(WordsListUiState.Loading)
    val wordsListUiState: StateFlow<WordsListUiState> = _wordsListUiState.asStateFlow()

    /* var wordsListUiState: WordsListUiState by mutableStateOf(WordsListUiState.Loading)
         private set*/

    init {
        getWords()
    }

    fun updateWord(word: String) {
        _newWordUiState.update { currentState ->
            currentState.copy(word = word)
        }
    }

    fun updateTranslation(translation: String) {
        _newWordUiState.update { currentState ->
            currentState.copy(translation = translation)
        }
    }

    fun saveNewWord() {
        newWordUiState.value.apply {
            if (isWordValid(word, translation)) {
                viewModelScope.launch {
                    addNewWord(word, translation)
                    resetNewWordState()
                }
            } else {
                _newWordUiState.update { currentState ->
                    currentState.copy(isInvalidWord = true)
                }
            }
        }
    }

    fun clearNewWord() {
        resetNewWordState()
    }

    private fun getWords() {
        viewModelScope.launch {
            _wordsListUiState.value = WordsListUiState.Loading
            getLastWords(10).collect { items ->
                _wordsListUiState.value = WordsListUiState.Success(items.map { wordUiMapper.map(it) })
            }
        }
    }

    private fun resetNewWordState() {
        _newWordUiState.update { currentState ->
            currentState.copy(word = "", translation = "", isInvalidWord = false)
        }
    }

    private fun isWordValid(word: String, translation: String) =
        word.isNotEmpty() && translation.isNotEmpty()
}
