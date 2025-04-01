package com.example.words_data.di

import android.content.Context
import com.example.words_data.datasource.WordsDatabase
import com.example.words_data.mapper.WordDBOToDomainMapper
import com.example.words_data.repository.WordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository(database: WordsDatabase, mapper: WordDBOToDomainMapper): WordsRepository =
        WordsRepository(database, mapper)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): WordsDatabase = WordsDatabase(context)
}