package com.example.home.presentation.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.CategoriesListUiState
import com.example.home.NewWordUiState
import com.example.home.R
import com.example.home.WordsListUiState
import com.example.home.di.HomeComponent
import com.example.home.presentation.HomeViewModel
import com.example.home.presentation.models.CategoryUI
import com.example.home.presentation.models.WordUI

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModelFactory = remember {
        val component = HomeComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(HomeViewModel::class, factory = viewModelFactory)

    val newWordUiState by viewModel.newWordUiState.collectAsState()
    val wordsListUiState by viewModel.wordsListUiState.collectAsState()
    val categoriesListUiState by viewModel.categoriesListUiState.collectAsState()

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
        CategoriesList(categoriesListUiState, wordsListUiState)
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
fun CategoriesList(categoriesListState: CategoriesListUiState, wordsListUiState: WordsListUiState) {
    when (categoriesListState) {
        is CategoriesListUiState.Loading -> if (wordsListUiState != WordsListUiState.Loading) DataLoading()
        is CategoriesListUiState.Success -> CategoriesList(
            categories = categoriesListState.categories
        )
        is CategoriesListUiState.Error -> DataLoadingError()
    }
}

@Composable
fun CategoriesList(categories: List<CategoryUI>) {
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
fun Category(category: CategoryUI) {
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
            Text(text = category.name, fontSize = 20.sp)
            Text(
                text = pluralStringResource(
                    R.plurals.category_words_count,
                    category.wordsCount,
                    category.wordsCount
                ), fontSize = 16.sp
            )
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
