package com.codeplace.postsandroidapp.core.presentation.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed interface ScreenRoutes {

    @Serializable
    data object HomeGraph : ScreenRoutes

    @Serializable
    data object Explore : ScreenRoutes

    @Serializable
    data object Favourites : ScreenRoutes

    @Serializable
    data class Comments(val postId: Int) : ScreenRoutes

    @Serializable
    data object Settings : ScreenRoutes

    @Serializable
    data object Theme: ScreenRoutes

}

