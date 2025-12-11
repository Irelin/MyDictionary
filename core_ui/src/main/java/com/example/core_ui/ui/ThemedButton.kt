package com.example.core_ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.core_ui.R

@Composable
fun ThemedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.button_primary_color)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text = text, color = colorResource(R.color.button_text))
    }
}

@Composable
fun ThemedOutlinedButton(onClick: () -> Unit,
                 text: String,
                 modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(1.dp, colorResource(R.color.button_primary_color)),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text = text, color = colorResource(R.color.button_text))
    }
}