package com.example.words.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.words.di.WordsComponent
import com.example.words_ui.WordsListUiState
import com.example.words_ui.ui.WordsList

@Composable
fun WordsListScreen(categoryId: Long) {
    val viewModelFactory = remember {
        val component = WordsComponent.create(categoryId)
        component.provideViewModelFactory()
    }

    val viewModel = viewModel(WordsListViewModel::class, factory = viewModelFactory)

    val wordsListUiState by viewModel.wordsListUiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryTitle(wordsListUiState)
        WordsList(wordsListUiState) { }
    }
}

@Composable
fun CategoryTitle(wordsListState: WordsListUiState) {
    if (wordsListState is WordsListUiState.Success) {
        Text(
            text = "${wordsListState.categoryName} - ${wordsListState.words.size}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
