package com.example.core.di

import android.content.Context
import com.example.words_api.WordsApi

interface AppComponent: WordsApi {

    val context: Context
}
