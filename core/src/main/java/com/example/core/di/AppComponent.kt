package com.example.core.di

import android.content.Context
import com.example.categories_api.CategoriesApi
import com.example.profile_api.ProfileApi
import com.example.words_api.WordsApi

interface AppComponent: WordsApi, CategoriesApi, ProfileApi {

    val context: Context
}
