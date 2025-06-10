package com.example.profile_impl.di

import com.example.core_data.datasource.UserManager
import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_api.domain.usecase.SetUserName
import com.example.profile_impl.domain.usecase.GetUserNameUseCase
import com.example.profile_impl.domain.usecase.SetUserNameUseCase
import com.example.profile_impl.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProfileApiModule {
    @Provides
    @Singleton
    fun provideRepository(userManager: UserManager): UserRepository =
        UserRepository(userManager)

    @Provides
    @Singleton
    fun provideGetUserName(userRepository: UserRepository): GetUserName =
        GetUserNameUseCase(userRepository)

    @Provides
    @Singleton
    fun provideSetUserName(userRepository: UserRepository): SetUserName =
        SetUserNameUseCase(userRepository)
}