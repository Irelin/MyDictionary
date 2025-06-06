package com.example.words.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.words.presentation.WordsListViewModel
import com.example.words_api.domain.usecase.GetCategoryWords
import com.example.words_ui.mapper.WordUiMapper
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class WordsModule(private val categoryId: Long) {
    @Provides
    @WordsScope
    fun provideViewModel(
        getCategoryWords: GetCategoryWords,
        wordUiMapper: WordUiMapper
    ): WordsListViewModel {
        return WordsListViewModel(
            getCategoryWords,
            wordUiMapper,
            categoryId
        )
    }

    @Provides
    fun provideViewModelFactory(
        wordsViewModel: Provider<WordsListViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                wordsViewModel.get()
            }
        }
    }
}
