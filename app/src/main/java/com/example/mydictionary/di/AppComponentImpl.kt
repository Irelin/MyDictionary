package com.example.mydictionary.di

import android.content.Context
import com.example.core.di.AppComponent
import com.example.words_data.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DatabaseModule::class,
])
interface AppComponentImpl: AppComponent {

    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance context: Context): AppComponentImpl
    }
}
