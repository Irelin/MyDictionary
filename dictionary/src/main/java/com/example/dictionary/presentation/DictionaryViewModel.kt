package com.example.dictionary.presentation

import androidx.lifecycle.ViewModel
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetAllWords
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
    private val getAllWords: GetAllWords, private val addWord: AddWord,
    private val getAllCategories: GetAllCategories,
    private val addCategory: AddCategory
) :
    ViewModel() {

}