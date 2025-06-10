package com.example.mydictionary.di

import android.content.Context
import com.example.categories_impl.di.CategoriesApiModule
import com.example.core.di.AppComponent
import com.example.core_data.di.DataStoreModule
import com.example.core_data.di.DatabaseModule
import com.example.profile_impl.di.ProfileApiModule
import com.example.words_impl.di.WordsApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DatabaseModule::class,
    DataStoreModule::class,
    WordsApiModule::class,
    CategoriesApiModule::class,
    ProfileApiModule::class
])
interface AppComponentImpl: AppComponent {

    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance context: Context): AppComponentImpl
    }
}
