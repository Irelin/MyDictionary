package com.example.mydictionary.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mydictionary.ui.theme.MyDictionaryTheme

@Composable
fun DictionaryApp() {
    MyDictionaryTheme {
        val navController = rememberNavController()
        var selectedBottomTab by rememberSaveable { mutableIntStateOf(BottomNavigationRoutes.HOME.id) }

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                BottomNavigationBar(selectedBottomTab) {
                    if (selectedBottomTab != it.id) {
                        navController.navigate(route = it.route)
                        selectedBottomTab = it.id
                    }
                }
            }
        ) { innerPaddingModifier ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier),
                onInnerNavigation = { selectedBottomTab = it }
            )
        }
    }
}