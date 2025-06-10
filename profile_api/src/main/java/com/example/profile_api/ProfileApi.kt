package com.example.profile_api

import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_api.domain.usecase.SetUserName

interface ProfileApi {
    val getUserName: GetUserName
    val setUserName: SetUserName
}