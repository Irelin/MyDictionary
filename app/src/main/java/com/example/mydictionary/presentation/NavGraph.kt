package com.example.mydictionary.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.categories.presentation.CategoriesListScreen
import com.example.categories.presentation.CategoryWordsScreen
import com.example.home.presentation.ui.HomeScreen
import com.example.study.presentation.ui.StudyScreen
import kotlinx.serialization.Serializable

// main routes
@Serializable
object Home

@Serializable
object Study

// route for nested dictionary graph
@Serializable
object Dictionary

// routes inside nested dictionary graph
@Serializable
object CategoriesList

@Serializable
data class CategoryWords(val id: Long)

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onInnerNavigation: (Int) -> Unit
) {
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                modifier = modifier,
                {
                    navController.navigate(route = CategoriesList)
                    onInnerNavigation(BottomNavigationRoutes.DICTIONARY.id)
                },
                {
                    navController.navigate(route = CategoryWords(it))
                    onInnerNavigation(BottomNavigationRoutes.DICTIONARY.id)
                })
        }
        composable<Study> { StudyScreen() }
        navigation<Dictionary>(startDestination = CategoriesList) {
            composable<CategoriesList> {
                CategoriesListScreen {
                    navController.navigate(
                        route = CategoryWords(it)
                    )
                }
            }
            composable<CategoryWords> { backStackEntry ->
                val categoryInfo: CategoryWords = backStackEntry.toRoute()
                CategoryWordsScreen(categoryInfo.id)
            }
        }
    }
}