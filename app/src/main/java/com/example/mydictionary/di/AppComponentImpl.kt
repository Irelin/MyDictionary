package com.example.mydictionary.di

import android.content.Context
import com.example.core.di.AppComponent
import com.example.core_data.di.DatabaseModule
import com.example.words_impl.di.WordsApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DatabaseModule::class,
    WordsApiModule::class
])
interface AppComponentImpl: AppComponent {

    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance context: Context): AppComponentImpl
    }
}
