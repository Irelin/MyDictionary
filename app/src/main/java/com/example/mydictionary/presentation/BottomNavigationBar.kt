package com.example.mydictionary.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
        ), containerColor = Color(0xFFFFEAE1)
    ) {
        BottomNavigationRoutes.entries.forEach { route ->
            NavigationBarItem(
                icon = { Icon(painterResource(route.icon), contentDescription = null) },
                label = { Text(stringResource(route.title).uppercase(Locale.getDefault())) },
                selected = selectedDestination == route.id,
                onClick = { onTabClick(route) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(com.example.home.R.color.color_selected),
                    selectedTextColor = colorResource(com.example.home.R.color.color_selected),
                    unselectedIconColor = colorResource(com.example.home.R.color.text_secondary_color),
                    unselectedTextColor = colorResource(com.example.home.R.color.text_secondary_color),
                    indicatorColor = Color.Transparent
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
    VOCABULARY(2, R.string.route_vocabulary_title, R.drawable.ic_tab_vocabulary, CategoriesList),
    PROFILE(3, R.string.route_profile_title, R.drawable.ic_tab_profile, Profile)
}