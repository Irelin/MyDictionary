package com.example.words_api

import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetLastWords
import com.example.words_api.domain.usecase.GetAllWords
import com.example.words_api.domain.usecase.GetCategoryWords

interface WordsApi {
    val addWord: AddWord
    val getLastWords: GetLastWords
    val getAllWords: GetAllWords
    val getCategoryWords: GetCategoryWords
}