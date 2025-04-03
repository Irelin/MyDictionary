package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.home.domain.AddWordUseCase
import com.example.home.domain.GetLastWordsUseCase
import com.example.home.presentation.HomeViewModel
import com.example.home.presentation.ui.WordUiMapper
import com.example.home_api.usecase.AddWord
import com.example.words_data.repository.WordsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class HomeModule {
    @Provides
    @HomeScope
    fun provideViewModel(
        //getLastWords: GetLastWordsUseCase,
        //addWord: AddWord,
        wordUiMapper: WordUiMapper
    ): HomeViewModel {
        return HomeViewModel(
            //getLastWords,
            //addWord,
            wordUiMapper,
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
