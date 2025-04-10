package com.example.categories_api

import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_api.domain.usecase.GetLastCategories

interface CategoriesApi {
    val getLastCategories: GetLastCategories
    val getCategories: GetAllCategories
}