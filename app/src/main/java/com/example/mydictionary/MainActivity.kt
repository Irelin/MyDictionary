package com.example.mydictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mydictionary.presentation.DictionaryApp
import com.example.mydictionary.ui.theme.MyDictionaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDictionaryTheme {
                DictionaryApp()
            }
        }
    }
}