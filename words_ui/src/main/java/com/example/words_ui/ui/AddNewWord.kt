package com.example.words_ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.words_ui.NewWordUiState
import com.example.words_ui.R

@Composable
fun AddNewWord(
    newWordUiState: NewWordUiState,
    onWordChange: (String) -> Unit,
    onTranslationChange: (String) -> Unit,
    onCategoryClick: () -> Unit,
    onSaveClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Text(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        textAlign = TextAlign.Companion.Start,
        text = stringResource(R.string.new_word_title),
        fontSize = 20.sp,
        fontWeight = FontWeight.Companion.Bold
    )
    OutlinedTextField(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        value = newWordUiState.word,
        onValueChange = onWordChange,
        label = { Text(stringResource(R.string.new_word_text)) },
        isError = newWordUiState.word.isEmpty() && newWordUiState.isInvalidWord
    )
    OutlinedTextField(
        modifier = Modifier.Companion
            .fillMaxWidth(),
        value = newWordUiState.translation,
        onValueChange = onTranslationChange,
        label = { Text(stringResource(R.string.new_word_translation)) },
        isError = newWordUiState.translation.isEmpty() && newWordUiState.isInvalidWord
    )
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        OutlinedButton(onClick = onCategoryClick) {
            Text(text = stringResource(R.string.new_word_category_btn))
        }
        Spacer(
            modifier = Modifier.Companion
                .height(1.dp)
                .weight(1f)
        )
        OutlinedButton(onClick = onClearClick, modifier = Modifier.Companion.padding(horizontal = 8.dp)) {
            Text(text = stringResource(R.string.word_clear_btn))
        }
        Button(onClick = onSaveClick) {
            Text(text = stringResource(R.string.word_save_btn))
        }
    }
}