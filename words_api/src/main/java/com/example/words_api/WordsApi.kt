package com.example.words_api

import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetLastWords

interface WordsApi {
    val addWord: AddWord
    val getLastWords: GetLastWords
}