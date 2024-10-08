package com.codeplace.postsandroidapp.core.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoutes() {

    @Serializable
    data object HomeGraph : ScreenRoutes()

    @Serializable
    data object Explore : ScreenRoutes()

    @Serializable
    data class Comments(val postId: Int) : ScreenRoutes()

    @Serializable
    data object Search : ScreenRoutes()

    @Serializable
    data object Favorites : ScreenRoutes()

    @Serializable
    data object Settings : ScreenRoutes()

    @Serializable
    data object Theme: ScreenRoutes()


}

