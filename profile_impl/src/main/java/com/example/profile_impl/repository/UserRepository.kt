package com.example.profile_impl.repository

import com.example.core_data.datasource.UserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userManager: UserManager) {
    suspend fun setUserName(name: String) {
        userManager.saveUserName(name)
    }

    fun getUserName(): Flow<String> {
        return userManager.getUserName()
    }
}