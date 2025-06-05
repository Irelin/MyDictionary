package com.example.dictionary.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.dictionary.presentation.DictionaryViewModel
import dagger.Component

@Component(
    modules = [DictionaryModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@DictionaryScope
interface DictionaryComponent {

    fun getViewModel(): DictionaryViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): DictionaryComponent {
            return DaggerDictionaryComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}
