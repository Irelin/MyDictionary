package com.example.mydictionary.di

import android.content.Context
import com.example.mydictionary.BuildConfig
import com.example.mydictionary.network.TranslateApi
import com.example.words_data.datasource.WordsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WordsDatabase {
        return WordsDatabase(context)
    }
}