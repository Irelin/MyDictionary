package com.example.words_impl.di

import com.example.core_data.dao.WordsDao
import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetLastWords
import com.example.words_api.domain.usecase.GetAllWords
import com.example.words_api.domain.usecase.GetCategoryWords
import com.example.words_impl.domain.usecase.AddWordUseCase
import com.example.words_impl.domain.usecase.GetLastWordsUseCase
import com.example.words_impl.domain.usecase.GetAllWordsUseCase
import com.example.words_impl.domain.usecase.GetCategoryWordsUseCase
import com.example.words_impl.mapper.WordDBOToDomainMapper
import com.example.words_impl.repository.WordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WordsApiModule {
    @Provides
    @Singleton
    fun provideRepository(wordsDao: WordsDao, mapper: WordDBOToDomainMapper): WordsRepository =
        WordsRepository(wordsDao, mapper)

    @Provides
    @Singleton
    fun provideAddWord(wordsRepository: WordsRepository): AddWord =
        AddWordUseCase(wordsRepository)

    @Provides
    @Singleton
    fun provideGetLastWords(wordsRepository: WordsRepository): GetLastWords =
        GetLastWordsUseCase(wordsRepository)

    @Provides
    @Singleton
    fun provideGetWords(wordsRepository: WordsRepository): GetAllWords =
        GetAllWordsUseCase(wordsRepository)

    @Provides
    @Singleton
    fun provideGetCategoryWords(wordsRepository: WordsRepository): GetCategoryWords =
        GetCategoryWordsUseCase(wordsRepository)
}