package com.example.home.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.NewWordUiState
import com.example.home.R
import com.example.home.WordsListUiState
import com.example.home.di.HomeComponent
import com.example.home.presentation.ui.WordUI

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModelFactory = remember {
        val component = HomeComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(HomeViewModel::class, factory = viewModelFactory)

    val newWordUiState by viewModel.newWordUiState.collectAsState()
    val wordsListUiState by viewModel.wordsListUiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Profile()
        LogoDictionary()
        AddNewWord(
            newWordUiState = newWordUiState,
            onWordChange = { viewModel.updateWord(it) },
            onTranslationChange = { viewModel.updateTranslation(it) },
            onSaveClick = { viewModel.saveNewWord() },
            onClearClick = { viewModel.clearNewWord() })
        WordsList(wordsListUiState)
        CategoriesList(wordsListUiState)
    }
}

@Composable
fun Profile() {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.account_image_default),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.profile_hi_user, "User"),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.profile_learned_words, 50, 100),
                fontSize = 14.sp,
                color = Color.Gray
            )
            LinearProgressIndicator(
                progress = { 0.5f },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.Red,
                trackColor = Color.Gray
            )
        }
    }
}

@Composable
fun LogoDictionary() {
    Image(
        painter = painterResource(id = R.drawable.home_dictionary_logo),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
    )
}

@Composable
fun AddNewWord(
    newWordUiState: NewWordUiState,
    onWordChange: (String) -> Unit,
    onTranslationChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onClearClick: () -> Unit
) {
    /*Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {*/
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Start,
        text = stringResource(R.string.new_word_title),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = newWordUiState.word,
        onValueChange = onWordChange,
        label = { Text(stringResource(R.string.new_word_text)) },
        isError = newWordUiState.word.isEmpty() && newWordUiState.isInvalidWord
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = newWordUiState.translation,
        onValueChange = onTranslationChange,
        label = { Text(stringResource(R.string.new_word_translation)) },
        isError = newWordUiState.translation.isEmpty() && newWordUiState.isInvalidWord
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(onClick = onSaveClick) {
            Text(text = stringResource(R.string.new_word_save_btn))
        }
        Button(onClick = onClearClick, modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = stringResource(R.string.new_word_clear_btn))
        }
        Button(onClick = onSaveClick) {
            Text(text = stringResource(R.string.new_word_category_btn))
        }
    }
    //}
}

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
fun ListTitle(@StringRes titleRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(titleRes),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        TextButton(
            onClick = {}) {
            Text(stringResource(R.string.view_all))
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

@Composable
fun DataLoadingError() {
    Text(text = stringResource(R.string.data_loading_error))
}

@Composable
fun DataLoading() {
    CircularProgressIndicator()
}

@Composable
fun CategoriesList(wordsListState: WordsListUiState) {
    when (wordsListState) {
        is WordsListUiState.Loading -> DataLoading()
        is WordsListUiState.Success -> CategoriesList(
            categories = wordsListState.words
        )
        is WordsListUiState.Error -> DataLoadingError()
    }
}

@Composable
fun CategoriesList(categories: List<WordUI>) {
    if (categories.isEmpty())
        return
    ListTitle(R.string.my_categories_title)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(categories.sortedByDescending { it.id }) {
            key(it.id) {
                Category(it)
            }
        }
    }
}

@Composable
fun Category(category: WordUI) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_category_default),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f)
        ) {
            Text(text = category.originValue, fontSize = 20.sp)
            Text(text = stringResource(R.string.my_category_words_count, 5), fontSize = 16.sp)
        }
        Image(
            painter = painterResource(id = R.drawable.icon_category_view_arrow),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Preview
@Composable
fun AccountPreview() {
    Profile()
}

@Preview
@Composable
fun AddNewWordPreview() {
    AddNewWord(NewWordUiState(), {}, {}, {}, {})
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
