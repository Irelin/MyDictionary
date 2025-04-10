package com.example.study.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.study.presentation.StudyViewModel
import dagger.Component

@Component(modules = [StudyModule::class], dependencies = [AppComponent::class])
@StudyScope
interface StudyComponent {
    fun getViewModel(): StudyViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): StudyComponent {
            return DaggerStudyComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}