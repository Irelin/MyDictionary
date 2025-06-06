package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetLastCategories
import com.example.categories_ui.CategoriesListUiState
import com.example.categories_ui.NewCategoryUiState
import com.example.words_ui.NewWordUiState
import com.example.words_ui.WordsListUiState
import com.example.categories_ui.mapper.CategoryUiMapper
import com.example.words_ui.mapper.WordUiMapper
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
    private val addNewCategory: AddCategory,
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

    private val _newCategoryUiState = MutableStateFlow(NewCategoryUiState())
    val newCategoryUiState: StateFlow<NewCategoryUiState> = _newCategoryUiState.asStateFlow()

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
                    addNewWord(word, translation, categories)
                    resetNewWordState()
                }
            } else {
                _newWordUiState.update { currentState ->
                    currentState.copy(isInvalidWord = true)
                }
            }
        }
    }

    fun saveNewCategory() {
        newCategoryUiState.value.apply {
            viewModelScope.launch {
                addNewCategory(name)
                updateNewCategory("")
            }
        }
    }

    fun clearNewWord() {
        resetNewWordState()
    }

    fun updateNewCategory(category: String) {
        _newCategoryUiState.update { currentState ->
            currentState.copy(name = category)
        }
    }

    fun addWordCategory(id: Long) {
        val newCategories = _newWordUiState.value.categories
        newCategories.add(id)
        updateWordCategories(newCategories)
    }

    fun removeWordCategory(id: Long) {
        val newCategories = _newWordUiState.value.categories
        newCategories.remove(id)
        updateWordCategories(newCategories)
    }

    fun resetWordCategories() {
        updateWordCategories(mutableListOf<Long>())
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

    private fun updateWordCategories(newCategories: MutableList<Long>) {
        _newWordUiState.update { currentState ->
            currentState.copy(categories = newCategories)
        }
    }

    private fun isWordValid(word: String, translation: String) =
        word.isNotEmpty() && translation.isNotEmpty()
}
