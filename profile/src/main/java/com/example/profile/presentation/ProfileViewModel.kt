package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_api.domain.usecase.SetUserName
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserName: GetUserName,
    private val setUserName: SetUserName
): ViewModel() {
}