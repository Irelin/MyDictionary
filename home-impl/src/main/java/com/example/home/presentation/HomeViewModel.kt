package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories_api.domain.usecase.GetLastCategories
import com.example.home.CategoriesListUiState
import com.example.home.NewWordUiState
import com.example.home.WordsListUiState
import com.example.home.presentation.mapper.CategoryUiMapper
import com.example.home.presentation.mapper.WordUiMapper
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
    private val getLastCategories: GetLastCategories,
    private val wordUiMapper: WordUiMapper,
    private val categoryUiMapper: CategoryUiMapper,
) : ViewModel() {

    companion object {
        const val MAX_WORDS_COUNT = 4
        const val MAX_CATEGORIES_COUNT = 4
    }

    private val _newWordUiState = MutableStateFlow(NewWordUiState())
    val newWordUiState: StateFlow<NewWordUiState> = _newWordUiState.asStateFlow()

    private val _wordsListUiState: MutableStateFlow<WordsListUiState> =
        MutableStateFlow(WordsListUiState.Loading)
    val wordsListUiState: StateFlow<WordsListUiState> = _wordsListUiState.asStateFlow()

    private val _categoriesListUiState: MutableStateFlow<CategoriesListUiState> =
        MutableStateFlow(CategoriesListUiState.Loading)
    val categoriesListUiState: StateFlow<CategoriesListUiState> = _categoriesListUiState.asStateFlow()

    /* var wordsListUiState: WordsListUiState by mutableStateOf(WordsListUiState.Loading)
         private set*/

    init {
        getWords()
        getCategories()
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
            getLastWords(MAX_WORDS_COUNT).collect { items ->
                _wordsListUiState.value = WordsListUiState.Success(items.map { wordUiMapper.map(it) })
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoriesListUiState.value = CategoriesListUiState.Loading
            getLastCategories(MAX_CATEGORIES_COUNT).collect { items ->
                _categoriesListUiState.value = CategoriesListUiState.Success(items.map { categoryUiMapper.map(it) })
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
