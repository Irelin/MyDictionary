package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_ui.CategoriesListUiState
import com.example.categories_ui.NewCategoryUiState
import com.example.words_ui.NewWordUiState
import com.example.words_ui.WordsListUiState
import com.example.categories_ui.mapper.CategoryUiMapper
import com.example.profile_api.domain.usecase.GetUserName
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
    private val getUserName: GetUserName,
    private val getLastWords: GetLastWords,
    private val addNewWord: AddWord,
    private val getAllCategories: GetAllCategories,
    private val addNewCategory: AddCategory,
    private val wordUiMapper: WordUiMapper,
    private val categoryUiMapper: CategoryUiMapper,
) : ViewModel() {

    companion object {
        const val MAX_WORDS_COUNT = 4
        const val MAX_CATEGORIES_COUNT = 4
    }

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _newWordUiState = MutableStateFlow(NewWordUiState())
    val newWordUiState: StateFlow<NewWordUiState> = _newWordUiState.asStateFlow()

    private val _lastWordsListUiState: MutableStateFlow<WordsListUiState> =
        MutableStateFlow(WordsListUiState.Loading)
    val lastWordsListUiState: StateFlow<WordsListUiState> = _lastWordsListUiState.asStateFlow()

    private val _newCategoryUiState = MutableStateFlow(NewCategoryUiState())
    val newCategoryUiState: StateFlow<NewCategoryUiState> = _newCategoryUiState.asStateFlow()

    private val _categoriesListUiState: MutableStateFlow<CategoriesListUiState> =
        MutableStateFlow(CategoriesListUiState.Loading)
    val categoriesListUiState: StateFlow<CategoriesListUiState> =
        _categoriesListUiState.asStateFlow()

    private val _lastCategoriesListUiState: MutableStateFlow<CategoriesListUiState> =
        MutableStateFlow(CategoriesListUiState.Loading)
    val lastCategoriesListUiState: StateFlow<CategoriesListUiState> =
        _lastCategoriesListUiState.asStateFlow()

    init {
        loadUserName()
        loadLastWords()
        loadCategories()
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

    private fun loadUserName() {
        viewModelScope.launch {
            getUserName().collect { _userName.value = it }
        }
    }

    private fun loadLastWords() {
        viewModelScope.launch {
            _lastWordsListUiState.value = WordsListUiState.Loading
            getLastWords(MAX_WORDS_COUNT).collect { items ->
                _lastWordsListUiState.value =
                    WordsListUiState.Success(items.map { wordUiMapper.map(it) })
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categoriesListUiState.value = CategoriesListUiState.Loading
            getAllCategories().collect { items ->
                _categoriesListUiState.value =
                    CategoriesListUiState.Success(items.map { categoryUiMapper.map(it) })
                _lastCategoriesListUiState.value = CategoriesListUiState.Success(
                    items.take(MAX_CATEGORIES_COUNT).map { categoryUiMapper.map(it) })
            }
        }
    }

    private fun resetNewWordState() {
        _newWordUiState.update { currentState ->
            currentState.copy(word = "", translation = "", isInvalidWord = false, categories = mutableListOf())
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
