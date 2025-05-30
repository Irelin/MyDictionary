package com.example.dictionary.presentation

import androidx.lifecycle.ViewModel
import com.example.categories_api.domain.usecase.GetAllCategories
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(private val getAllCategories: GetAllCategories) :
    ViewModel() {

}