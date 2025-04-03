package com.example.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.NewWordUiState
import com.example.home.WordsListUiState
import com.example.home.di.DaggerHomeComponent
import com.example.home.di.HomeComponent
import com.example.home.presentation.ui.WordUI

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModelFactory = remember {
        val component = HomeComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(HomeViewModel::class, factory = viewModelFactory)
    Log.i("DICTIONARY_LOG", "recomposition, VM: $viewModel")

    val newWordUiState by viewModel.newWordUiState.collectAsState()
    val wordsListUiState by viewModel.wordsListUiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddNewWord(newWordUiState = newWordUiState,
            onWordChange = { viewModel.updateWord(it) },
            onTranslationChange = { viewModel.updateTranslation(it) },
            onSaveClick = { viewModel.saveNewWord() })
        WordsListSection(wordsListUiState)
    }
}

@Composable
fun AddNewWord(
    newWordUiState: NewWordUiState,
    onWordChange: (String) -> Unit,
    onTranslationChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Add new word", fontSize = 20.sp)
            OutlinedTextField(
                value = newWordUiState.word,
                onValueChange = onWordChange,
                label = { Text("Word") },
                isError = newWordUiState.word.isEmpty() && newWordUiState.isInvalidWord
            )
            OutlinedTextField(
                value = newWordUiState.translation,
                onValueChange = onTranslationChange,
                label = { Text("Translation") },
                isError = newWordUiState.translation.isEmpty() && newWordUiState.isInvalidWord
            )
            Button(onClick = onSaveClick, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Save")
            }
            Button(onClick = onSaveClick) {
                Text(text = "Choose category")
            }
        }
    }
}

@Composable
fun WordsListSection(wordsListState: WordsListUiState) {
    when (wordsListState) {
        is WordsListUiState.Loading -> WordsLoading()
        is WordsListUiState.Success -> WordsList(
            words = wordsListState.words
        )
        is WordsListUiState.Error -> EmptyWordsList()
    }
}

@Composable
fun WordsList(words: List<WordUI>) {
    if (words.isEmpty()) {
        EmptyWordsList()
        return
    }
    Text(text = "My words", fontSize = 20.sp)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(words) {
            key(it.id) {
                Word(it)
            }
        }
    }
    if (words.size == 10) {
        TextButton(onClick = {}) {
            Text("View all")
        }
    }
}

@Composable
fun Word(word: WordUI) {
    Text(text = "${word.originValue} - ${word.translatedValue}")
}

@Composable
fun EmptyWordsList() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "No words added")
    }
}

@Composable
fun WordsLoading() {
    Box(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CategoriesList(categories: List<WordUI>) {
    if (categories.isEmpty()) {
        EmptyWordsList()
        return
    }
    LazyColumn {
        items(categories.sortedByDescending { it.id }) {
            key(it.id) {
                Word(it)
            }
        }
    }
}

@Preview
@Composable
fun AddNewWordPreview() {
    AddNewWord(NewWordUiState(), {}, {}, {})
}

@Preview
@Composable
fun WordsListPreview() {
    WordsList(
        words = listOf(
            WordUI(1, "sehen", "see"),
            WordUI(1, "malen", "draw")
        )
    )
}
