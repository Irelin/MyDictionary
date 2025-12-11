package com.example.words_ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.words_ui.R
import com.example.words_ui.WordsListUiState
import com.example.words_ui.models.WordUI

@Composable
fun WordsList(wordsListState: WordsListUiState, title: @Composable () -> Unit) {
    when (wordsListState) {
        is WordsListUiState.Loading -> DataLoading()
        is WordsListUiState.Success -> WordsList(wordsListState.words, title)
        is WordsListUiState.Error -> DataLoadingError()
    }
}

@Composable
fun WordsList(words: List<WordUI>, title: @Composable () -> Unit) {
    if (words.isEmpty())
        return
    title()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 400.dp)
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(words) {
            key(it.id) {
                Word(it)
            }
        }
    }
}

@Composable
fun Word(word: WordUI) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F6F5))
            .border(
                border = BorderStroke(1.dp, Color(0xFFE8E7E6)),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .padding(8.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(text = word.originValue, fontSize = 20.sp)
        Text(text = word.translatedValue, fontSize = 16.sp)
    }
}

@Composable
fun DataLoadingError() {
    Text(text = stringResource(R.string.words_loading_error))
}

@Composable
fun DataLoading() {
    CircularProgressIndicator()
}