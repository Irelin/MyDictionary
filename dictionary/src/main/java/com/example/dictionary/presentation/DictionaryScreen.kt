package com.example.dictionary.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dictionary.di.DictionaryComponent

@Composable
fun DictionaryScreen() {
    val viewModelFactory = remember {
        val component = DictionaryComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(DictionaryViewModel::class, factory = viewModelFactory)
}