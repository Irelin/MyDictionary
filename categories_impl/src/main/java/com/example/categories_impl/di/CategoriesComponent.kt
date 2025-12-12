package com.example.categories_impl.di

import androidx.lifecycle.ViewModelProvider
import com.example.categories_impl.presentation.CategoriesListViewModel
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import dagger.Component

@Component(
    modules = [CategoriesModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@CategoriesScope
interface CategoriesComponent {

    fun getViewModel(): CategoriesListViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): CategoriesComponent {
            return DaggerCategoriesComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}
