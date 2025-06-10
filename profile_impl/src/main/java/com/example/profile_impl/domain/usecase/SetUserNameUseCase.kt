package com.example.profile_impl.domain.usecase

import com.example.profile_api.domain.usecase.SetUserName
import com.example.profile_impl.repository.UserRepository
import javax.inject.Inject

class SetUserNameUseCase @Inject constructor(private val userRepository: UserRepository): SetUserName {
    override suspend fun invoke(name: String) {
        userRepository.setUserName(name)
    }
}