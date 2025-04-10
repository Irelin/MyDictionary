package com.example.categories_api

import com.example.categories_api.domain.usecase.GetLastCategories

interface CategoriesApi {
    val getCategories: GetLastCategories
}