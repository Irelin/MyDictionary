package com.example.profile_api.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetUserName {
    operator fun invoke(): Flow<String>
}