package com.example.profile_impl.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_api.domain.usecase.SetUserName
import com.example.profile_impl.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ProfileModule {
    @Provides
    @ProfileScope
    fun provideViewModel(
        getUserName: GetUserName,
        setUserName: SetUserName
    ): ProfileViewModel {
        return ProfileViewModel(
            getUserName,
            setUserName
        )
    }

    @Provides
    fun provideViewModelFactory(
        profileViewModel: Provider<ProfileViewModel>
    ): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                profileViewModel.get()
            }
        }
    }
}