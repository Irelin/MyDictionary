package com.example.core_data.di

import android.content.Context
import com.example.core_data.dao.WordsDao
import com.example.core_data.datasource.WordsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): WordsDatabase = WordsDatabase(context)

    @Provides
    @Singleton
    fun provideWordsDao(wordsDatabase: WordsDatabase): WordsDao = wordsDatabase.wordsDao()
}