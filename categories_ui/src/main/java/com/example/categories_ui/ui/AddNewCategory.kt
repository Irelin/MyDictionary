package com.example.categories_ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.categories_ui.NewCategoryUiState
import com.example.categories_ui.R

@Composable
fun AddNewCategory(
    newCategoryUiState: NewCategoryUiState,
    onNewCategoryCancel: () -> Unit,
    onWordChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp),
        value = newCategoryUiState.name,
        onValueChange = onWordChange,
        label = { Text(stringResource(R.string.new_category_text)) }
        //isError = chooseCategoriesUiState.newCategory.isEmpty() && chooseCategoriesUiState.isInvalidWord
    )
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = onNewCategoryCancel,
            modifier = Modifier.Companion.padding(horizontal = 8.dp)
        ) {
            Text(text = stringResource(R.string.category_cancel_btn))
        }
        Button(onClick = onSaveClick) {
            Text(text = stringResource(R.string.category_save_btn))
        }
    }
}