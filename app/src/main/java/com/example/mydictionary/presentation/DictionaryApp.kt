package com.example.mydictionary.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mydictionary.ui.theme.MyDictionaryTheme

@Composable
fun DictionaryApp() {
    MyDictionaryTheme {
        val tabs = remember { NavBottomTabs.entries.toTypedArray() }
        val navController = rememberNavController()
        Scaffold(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            bottomBar = { NavBottomBar(navController = navController, tabs) }
        ) { innerPaddingModifier ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier)
            )
        }
    }
}