package com.example.categories_ui.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.categories_ui.CategoriesListUiState
import com.example.categories_ui.R
import com.example.categories_ui.models.CategoryUI

@Composable
fun CategoriesList(categoriesListState: CategoriesListUiState, title: @Composable () -> Unit) {
    when (categoriesListState) {
        is CategoriesListUiState.Loading -> DataLoading()
        is CategoriesListUiState.Success -> CategoriesList(categoriesListState.categories, title)
        is CategoriesListUiState.Error -> DataLoadingError()
    }
}

@Composable
fun CategoriesList(categories: List<CategoryUI>, title: @Composable () -> Unit) {
    if (categories.isEmpty())
        return
    title()
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
    CategoryItem(category) {
        Image(
            painter = painterResource(id = R.drawable.icon_category_view_arrow),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Composable
fun CategoryItem(category: CategoryUI, rightButton: @Composable () -> Unit) {
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
        rightButton()
    }
}

@Composable
fun DataLoadingError() {
    Text(text = stringResource(R.string.categories_loading_error))
}

@Composable
fun DataLoading() {
    CircularProgressIndicator()
}