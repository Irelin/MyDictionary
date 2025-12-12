package com.example.study_impl.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.study_impl.presentation.StudyViewModel
import com.example.study_impl.presentation.mapper.StudyWordUiMapper
import com.example.words_api.domain.usecase.GetLastWords
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class StudyModule {
    @Provides
    @StudyScope
    fun provideViewModel(
        getLastWords: GetLastWords,
        studyWordUiMapper: StudyWordUiMapper
    ): StudyViewModel {
        return StudyViewModel(
            getLastWords, studyWordUiMapper
        )
    }

    @Provides
    fun provideViewModelFactory(
        studyViewModel: Provider<StudyViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                studyViewModel.get()
            }
        }
    }
}