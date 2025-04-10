package com.example.categories_impl.di

import com.example.categories_api.domain.usecase.GetAllCategories
import com.example.categories_api.domain.usecase.GetLastCategories
import com.example.categories_impl.domain.usecase.GetAllCategoriesUseCase
import com.example.categories_impl.domain.usecase.GetLastCategoriesUseCase
import com.example.categories_impl.mapper.CategoryInfoDBOToDomainMapper
import com.example.categories_impl.repository.CategoriesRepository
import com.example.core_data.dao.CategoriesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CategoriesApiModule {
    @Provides
    @Singleton
    fun provideRepository(categoriesDao: CategoriesDao, mapper: CategoryInfoDBOToDomainMapper): CategoriesRepository =
        CategoriesRepository(categoriesDao, mapper)

    @Provides
    @Singleton
    fun provideGetLastCategories(categoriesRepository: CategoriesRepository): GetLastCategories =
        GetLastCategoriesUseCase(categoriesRepository)

    @Provides
    @Singleton
    fun provideGetCategories(categoriesRepository: CategoriesRepository): GetAllCategories =
        GetAllCategoriesUseCase(categoriesRepository)
}