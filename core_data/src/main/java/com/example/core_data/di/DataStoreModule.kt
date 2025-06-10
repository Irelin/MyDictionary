package com.example.core_data.di

import android.content.Context
import com.example.core_data.datasource.DataStoreManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(context: Context): DataStoreManager = DataStoreManager(context)
}