package com.codeplace.postsandroidapp.core.presentation.components


import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.util.ScreenRoutes

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
        label = R.string.bottom_navigation_label_favorites,
        iconFilled = Icons.Filled.Favorite,
        iconOutlined = Icons.Outlined.FavoriteBorder,
        ScreenRoutes.Favorites
    ),
    SETTINGS (
       label = R.string.bottom_navigation_label_settings,
        iconFilled = Icons.Filled.Settings,
        iconOutlined = Icons.Outlined.Settings,
        ScreenRoutes.Settings
    )

}