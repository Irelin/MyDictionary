package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.presentation.ui.ProfileUiState
import com.example.profile_api.domain.usecase.GetUserName
import com.example.profile_api.domain.usecase.SetUserName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserName: GetUserName,
    private val setUserName: SetUserName
): ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    init {
        loadUserName()
    }

    private fun loadUserName() {
        viewModelScope.launch {
            getUserName().collect{ name -> {
                _profileUiState.value.userName = name
            }}
        }
    }

    public fun saveUserName(name: String) {
        viewModelScope.launch {
            setUserName(name)
            _profileUiState.value.userName = name
        }
    }
}