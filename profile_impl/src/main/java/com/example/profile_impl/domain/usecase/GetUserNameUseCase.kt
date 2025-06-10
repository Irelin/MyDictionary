package com.example.profile_impl.domain.usecase

import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_impl.repository.UserRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val userRepository: UserRepository): GetUserName {
    override suspend fun invoke() = userRepository.getUserName()
}