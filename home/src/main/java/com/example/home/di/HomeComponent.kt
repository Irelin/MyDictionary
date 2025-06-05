package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.home.presentation.HomeViewModel
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@HomeScope
interface HomeComponent {

    fun getViewModel(): HomeViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): HomeComponent {
            return DaggerHomeComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}
