package com.codeplace.postsandroidapp.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.navigation.ScreenRoutes

enum class BottomNavigation(
    val label: Int,
    val iconFilled: ImageVector,
    val iconOutlined: ImageVector,
    val route: ScreenRoutes,
) {
    EXPLORE(
        label = R.string.bottom_navigation_label_explore,
        iconFilled = Icons.Filled.Search,
        iconOutlined = Icons.Outlined.Search,
        route = ScreenRoutes.Explore
    ),
    FAVORITES(
        label = R.string.favourites,
        iconFilled = Icons.Filled.Favorite,
        iconOutlined = Icons.Outlined.FavoriteBorder,
        ScreenRoutes.Favourites
    ),
    SETTINGS (
       label = R.string.bottom_navigation_label_settings,
        iconFilled = Icons.Filled.Settings,
        iconOutlined = Icons.Outlined.Settings,
        ScreenRoutes.Settings
    )

}