package com.example.mydictionary.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.home.presentation.HomeScreen
import com.example.mydictionary.R
import com.example.study.presentation.StudyScreen
import java.util.Locale

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavDestinations.HOME_ROUTE) {
            HomeScreen(modifier = modifier)
        }
        composable(NavDestinations.STUDY_ROUTE) {
            StudyScreen()
        }
        composable(NavDestinations.PROFILE_ROUTE) {

        }
    }
}

@Composable
fun NavBottomBar(navController: NavController, tabs: Array<NavBottomTabs>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: NavDestinations.HOME_ROUTE

    NavigationBar(
        Modifier.windowInsetsBottomHeight(
            WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
        )
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
                selected = currentRoute == tab.route,
                onClick = {
                    if (tab.route != currentRoute) {
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = LocalContentColor.current,
                    unselectedTextColor = LocalContentColor.current
                ),
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

object NavDestinations {
    const val HOME_ROUTE = "home"
    const val STUDY_ROUTE = "study"
    const val PROFILE_ROUTE = "profile"
}

enum class NavBottomTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    HOME(R.string.route_home_title, R.drawable.ic_tab_home, NavDestinations.HOME_ROUTE),
    STUDY(R.string.route_study_title, R.drawable.ic_tab_study, NavDestinations.STUDY_ROUTE),
    PROFILE(R.string.route_profile_title, R.drawable.ic_tab_profile, NavDestinations.PROFILE_ROUTE)
}