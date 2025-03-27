package com.example.mydictionary.di

import com.example.mydictionary.BuildConfig
import com.example.mydictionary.network.TranslateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTranslateApi(): TranslateApi {
        return TranslateApi(
            baseUrl = BuildConfig.TRANSLATE_API_BASE_URL,
            apiKey = BuildConfig.TRANSLATE_API_KEY
        )
    }
}