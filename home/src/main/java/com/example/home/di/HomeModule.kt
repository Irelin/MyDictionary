package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetLastCategories
import com.example.home.presentation.HomeViewModel
import com.example.categories_ui.mapper.CategoryUiMapper
import com.example.words_ui.mapper.WordUiMapper
import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetLastWords
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class HomeModule {
    @Provides
    @HomeScope
    fun provideViewModel(
        getLastWords: GetLastWords,
        addWord: AddWord,
        getLastCategories: GetLastCategories,
        addCategory: AddCategory,
        wordUiMapper: WordUiMapper,
        categoryUiMapper: CategoryUiMapper
    ): HomeViewModel {
        return HomeViewModel(
            getLastWords,
            addWord,
            getLastCategories,
            addCategory,
            wordUiMapper,
            categoryUiMapper
        )
    }

    @Provides
    fun provideViewModelFactory(
        homeViewModel: Provider<HomeViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                homeViewModel.get()
            }
        }
    }

//    @Provides
//    @HomeScope
//    fun provideGetLastWords(
//        repository: WordsRepository
//    ): GetLastWordsUseCase =
//        GetLastWordsUseCase(repository)

//    @Provides
//    @HomeScope
//    fun provideAddWord(repository: WordsRepository): AddWord = AddWordUseCase(repository)
}
