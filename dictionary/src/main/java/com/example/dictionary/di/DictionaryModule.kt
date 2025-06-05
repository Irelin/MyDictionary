package com.example.dictionary.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.dictionary.presentation.DictionaryViewModel
import com.example.words_api.domain.usecase.AddWord
import com.example.words_api.domain.usecase.GetAllWords
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class DictionaryModule {
    @Provides
    @DictionaryScope
    fun provideViewModel(
        getAllWords: GetAllWords,
        addWord: AddWord,
        getAllCategories: GetAllCategories,
        addCategory: AddCategory/*,
        wordUiMapper: WordUiMapper,
        categoryUiMapper: CategoryUiMapper*/
    ): DictionaryViewModel {
        return DictionaryViewModel(
            getAllWords,
            addWord,
            getAllCategories,
            addCategory/*,
            wordUiMapper,
            categoryUiMapper*/
        )
    }

    @Provides
    fun provideViewModelFactory(
        homeViewModel: Provider<DictionaryViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                homeViewModel.get()
            }
        }
    }
}
