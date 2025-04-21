package com.example.categories_api.domain.usecase

interface AddCategory {
    suspend operator fun invoke(name: String)
}