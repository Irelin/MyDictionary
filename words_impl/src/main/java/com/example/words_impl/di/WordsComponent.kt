package com.example.words_impl.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.words_impl.presentation.WordsListViewModel
import dagger.Component

@Component(
    modules = [WordsModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@WordsScope
interface WordsComponent {

    fun getViewModel(): WordsListViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(categoryId: Long): WordsComponent {
            return DaggerWordsComponent
                .builder()
                .wordsModule(WordsModule(categoryId))
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}
