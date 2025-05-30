package com.example.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R
import com.example.home.WordsListUiState
import com.example.home.presentation.models.WordUI

@Composable
fun WordsList(wordsListState: WordsListUiState) {
    when (wordsListState) {
        is WordsListUiState.Loading -> DataLoading()
        is WordsListUiState.Success -> WordsList(
            words = wordsListState.words
        )

        is WordsListUiState.Error -> DataLoadingError()
    }
}

@Composable
fun WordsList(words: List<WordUI>) {
    if (words.isEmpty())
        return
    ListTitle(R.string.my_words_title)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 400.dp),
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
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(text = word.originValue, fontSize = 20.sp)
        Text(text = word.translatedValue, fontSize = 16.sp)
    }
}