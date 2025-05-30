package com.example.home.presentation.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.categories_ui.ui.CategoriesList
import com.example.categories_ui.ui.ChooseCategories
import com.example.home.NewWordUiState
import com.example.home.R
import com.example.home.di.HomeComponent
import com.example.home.presentation.HomeViewModel
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
    val newCategoryUiState by viewModel.newCategoryUiState.collectAsState()

    var chooseCategoriesOpen = remember { mutableStateOf(false) }

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
            onCategoryClick = { chooseCategoriesOpen.value = true },
            onSaveClick = { viewModel.saveNewWord() },
            onClearClick = { viewModel.clearNewWord() })
        WordsList(wordsListUiState)

        CategoriesList(categoriesListUiState) {
            ListTitle(R.string.my_categories_title)
        }
        if (chooseCategoriesOpen.value) {
            ChooseCategories(
                newCategoryUiState,
                categoriesListUiState,
                newWordUiState.categories,
                onDismiss = {
                    viewModel.resetWordCategories()
                    chooseCategoriesOpen.value = false
                },
                onSave = { chooseCategoriesOpen.value = false },
                onNewCategoryNameChange = { viewModel.updateNewCategory(it) },
                onNewCategorySaveClick = { viewModel.saveNewCategory() },
                onCategoryChecked = { categoryId, checked ->
                    if (checked)
                        viewModel.addWordCategory(categoryId)
                    else
                        viewModel.removeWordCategory(categoryId)
                }
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
fun DataLoadingError() {
    Text(text = stringResource(R.string.data_loading_error))
}

@Composable
fun DataLoading() {
    CircularProgressIndicator()
}

@Preview
@Composable
fun AccountPreview() {
    Profile()
}

@Preview
@Composable
fun AddNewWordPreview() {
    AddNewWord(NewWordUiState(), {}, {}, {}, {}, {})
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
