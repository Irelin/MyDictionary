package com.example.categories.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.categories.R
import com.example.categories.di.CategoriesComponent
import com.example.categories_ui.ui.CategoriesList

@Composable
fun CategoriesListScreen(onNavigateToCategory: (Long) -> Unit) {
    val viewModelFactory = remember {
        val component = CategoriesComponent.create()
        component.provideViewModelFactory()
    }
    val viewModel = viewModel(CategoriesListViewModel::class, factory = viewModelFactory)

    val categoriesListUiState by viewModel.categoriesListUiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.categories_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        CategoriesList(categoriesListUiState, onNavigateToCategory) { }
    }
}