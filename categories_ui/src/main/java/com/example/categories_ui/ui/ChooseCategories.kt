package com.example.categories_ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.categories_ui.CategoriesListUiState
import com.example.categories_ui.NewCategoryUiState
import com.example.categories_ui.R
import com.example.categories_ui.models.CategoryUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseCategories(
    newCategoryUiState: NewCategoryUiState,
    categoriesListUiState: CategoriesListUiState,
    selectedCategories: List<Long>,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onNewCategoryNameChange: (String) -> Unit,
    onNewCategorySaveClick: () -> Unit,
    onCategoryChecked: (Long, Boolean) -> Unit
) {
    var chooseCategoriesOpen = remember { mutableStateOf(true) }

    val onBackClick =
        if (chooseCategoriesOpen.value) onDismiss else {
            { chooseCategoriesOpen.value = true }
        }

    ModalBottomSheet(onDismissRequest = onDismiss, modifier = Modifier.fillMaxHeight()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val titleRes =
                if (chooseCategoriesOpen.value) R.string.choose_category_title else R.string.new_category_title

            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
            Text(
                text = stringResource(titleRes),
                fontSize = 20.sp,
                fontWeight = FontWeight.Companion.Bold
            )
            Spacer(
                modifier = Modifier.Companion
                    .height(1.dp)
                    .weight(1f)
            )
            IconButton(onClick = onDismiss) {
                Icon(Icons.Filled.Close, contentDescription = "")
            }
        }
        if (chooseCategoriesOpen.value) {
            if (categoriesListUiState is CategoriesListUiState.Success) {
                ChooseCategoriesList(
                    categoriesListUiState.categories,
                    selectedCategories,
                    onCategoryChecked,
                    onSave,
                    { chooseCategoriesOpen.value = false })
            }
        } else {
            AddNewCategory(
                newCategoryUiState,
                onBackClick,
                onNewCategoryNameChange,
                {
                    onNewCategorySaveClick()
                    chooseCategoriesOpen.value = true
                }
            )
        }
    }
}

@Composable
fun ChooseCategoriesList(
    categories: List<CategoryUI>,
    checkedCategories: List<Long>,
    onCategoryChecked: (Long, Boolean) -> Unit,
    onSave: () -> Unit,
    onNewCategoryClick: () -> Unit
) {
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onNewCategoryClick,
            modifier = Modifier.Companion.padding(horizontal = 8.dp)
        ) {
            Text(text = stringResource(R.string.new_category_btn))
        }
        Button(onClick = onSave) {
            Text(text = stringResource(R.string.category_save_btn))
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 1600.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(categories.sortedByDescending { it.id }) {
            key(it.id) {
                SelectableCategory(it, checkedCategories.contains(it.id), onCategoryChecked)
            }
        }
    }
}

@Composable
fun SelectableCategory(
    category: CategoryUI,
    checked: Boolean,
    onCategoryChecked: (Long, Boolean) -> Unit
) {
    CategoryItem(category, {}) {
        val checkedState = remember { mutableStateOf(checked) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                onCategoryChecked(category.id, it)
                checkedState.value = it
            }
        )
    }
}