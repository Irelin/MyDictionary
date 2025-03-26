package com.example.home_api.usecase

interface AddWord {
    suspend operator fun invoke(word: String, translation: String)
}