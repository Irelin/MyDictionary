package com.example.profile.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.AppComponent
import com.example.core.di.BaseApp
import com.example.profile.presentation.ProfileViewModel
import dagger.Component

@Component(
    modules = [ProfileModule::class],
    dependencies = [
        AppComponent::class,
    ]
)
@ProfileScope
interface ProfileComponent {
    fun getViewModel(): ProfileViewModel

    fun provideViewModelFactory(): ViewModelProvider.Factory

    companion object {

        fun create(): ProfileComponent {
            return DaggerProfileComponent
                .builder()
                .appComponent(BaseApp.appComponent)
                .build()
        }
    }
}