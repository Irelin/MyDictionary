package com.example.core.di

import android.content.Context
import com.example.categories_api.CategoriesApi
import com.example.words_api.WordsApi

interface AppComponent: WordsApi, CategoriesApi {

    val context: Context
}
