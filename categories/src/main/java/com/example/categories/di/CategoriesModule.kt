package com.example.categories.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.categories.presentation.CategoriesListViewModel
import com.example.categories_api.domain.usecase.AddCategory
import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_ui.mapper.CategoryUiMapper
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class CategoriesModule {
    @Provides
    @CategoriesScope
    fun provideViewModel(
        getAllCategories: GetAllCategories,
        addCategory: AddCategory,
        categoryUiMapper: CategoryUiMapper
    ): CategoriesListViewModel {
        return CategoriesListViewModel(
            getAllCategories,
            addCategory,
            categoryUiMapper
        )
    }

    @Provides
    fun provideViewModelFactory(
        categoriesViewModel: Provider<CategoriesListViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                categoriesViewModel.get()
            }
        }
    }
}
