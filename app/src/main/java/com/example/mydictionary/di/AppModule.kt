package com.example.mydictionary.di

import android.content.Context
import com.example.mydictionary.BuildConfig
import com.example.mydictionary.network.TranslateApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//@Module
class AppModule(private val appContext: Context) {
    /*@Provides
    @Singleton
    fun provideContext() = appContext*/

    /*@Provides
    @Singleton
    fun provideTranslateApi(): TranslateApi {
        return TranslateApi(
            baseUrl = BuildConfig.TRANSLATE_API_BASE_URL,
            apiKey = BuildConfig.TRANSLATE_API_KEY
        )
    }*/
}