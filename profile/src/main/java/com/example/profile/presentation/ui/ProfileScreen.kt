package com.example.profile.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val userNameState = viewModel.userNameState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameSection(userNameState) { viewModel.saveUserName(it) }
    }
}

@Composable
fun UserNameSection(userNameState: State<String>, onSave: (String) -> Unit) {
    var isNameEditing by remember { mutableStateOf(false) }
    val userName = userNameState.value.ifEmpty { "User name" }

    UserName(userName) { isNameEditing = true }
    AnimatedVisibility(
        visible = isNameEditing,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut()
    ) {
        EditUserName({
            onSave(it)
            isNameEditing = false
        }, { isNameEditing = false })
    }
}

@Composable
fun UserName(name: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(modifier = Modifier.weight(1f), text = name, fontSize = 18.sp)
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun EditUserName(onSave: (String) -> Unit, onClose: () -> Unit) {
    var newUserName by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 16.dp),
        value = newUserName,
        onValueChange = { newUserName = it },
        label = { Text("Enter new name") },
        trailingIcon = {
            Row {
                IconButton(
                    onClick = {
                        onSave(newUserName)
                    },
                    enabled = newUserName.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Clear text",
                        tint = if (newUserName.isNotEmpty()) Color.Green else Color.LightGray
                    )
                }
                IconButton(
                    onClick = onClose
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear text"
                    )
                }
            }

        }
    )
}