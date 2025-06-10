package com.example.profile_api.domain.usecase

interface SetUserName {
    suspend operator fun invoke(name: String)
}