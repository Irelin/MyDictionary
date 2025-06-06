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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mydictionary.R
import java.util.Locale

@Composable
fun BottomNavigationBar(
    selectedDestination: Int,
    onTabClick: (BottomNavigationRoutes) -> Unit
) {
    NavigationBar(
        Modifier.windowInsetsBottomHeight(
            WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
        )
    ) {
        BottomNavigationRoutes.entries.forEach { route ->
            NavigationBarItem(
                icon = { Icon(painterResource(route.icon), contentDescription = null) },
                label = { Text(stringResource(route.title).uppercase(Locale.getDefault())) },
                selected = selectedDestination == route.id,
                onClick = { onTabClick(route) },
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

enum class BottomNavigationRoutes(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: Any
) {
    HOME(0, R.string.route_home_title, R.drawable.ic_tab_home, Home),
    STUDY(1, R.string.route_study_title, R.drawable.ic_tab_exercise, Study),
    DICTIONARY(2, R.string.route_dictionary_title, R.drawable.ic_tab_dictionary, CategoriesList)
}