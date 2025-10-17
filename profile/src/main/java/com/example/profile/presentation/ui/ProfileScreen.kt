package com.example.profile.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profile.di.ProfileComponent
import com.example.profile.presentation.ProfileViewModel

@Composable
fun ProfileScreen() {
    val viewModelFactory = remember {
        val component = ProfileComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(ProfileViewModel::class, factory = viewModelFactory)
    val profileUiState by viewModel.profileUiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                modifier = Modifier.Companion
                    .fillMaxWidth().weight(1f),
                value = profileUiState.userName,
                onValueChange = { } // todo
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            }

        }
        Button(onClick = {

        }) {
            Text(text = "Save")
        }

    }
}