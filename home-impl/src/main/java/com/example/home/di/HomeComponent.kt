package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.home.presentation.HomeViewModel
import dagger.Component

@Component(
    modules = [HomeModule::class],
    //dependencies = [CoreComponent::class]
)
@HomeScope
interface HomeComponent {
    /*@Component.Builder
    interface Builder {
        fun build(): HomeComponent
    }*/

    fun getViewModel(): HomeViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): HomeComponent {
            return DaggerHomeComponent.builder()
                .build()
        }
    }
}
