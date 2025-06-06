package com.example.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_ui.CategoriesListUiState
import com.example.categories_ui.mapper.CategoryUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesListViewModel @Inject constructor(
    private val getAllCategories: GetAllCategories,
    private val addCategory: AddCategory,
    private val categoryUiMapper: CategoryUiMapper
) : ViewModel() {
    private val _categoriesListUiState: MutableStateFlow<CategoriesListUiState> =
        MutableStateFlow(CategoriesListUiState.Loading)
    val categoriesListUiState: StateFlow<CategoriesListUiState> =
        _categoriesListUiState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoriesListUiState.value = CategoriesListUiState.Loading
            getAllCategories().collect { items ->
                _categoriesListUiState.value = CategoriesListUiState.Success(items.map { categoryUiMapper.map(it) })
            }
        }
    }
}