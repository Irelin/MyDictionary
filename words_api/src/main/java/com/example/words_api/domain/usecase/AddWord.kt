package com.example.words_api.domain.usecase

interface AddWord {
    suspend operator fun invoke(word: String, translation: String)
}