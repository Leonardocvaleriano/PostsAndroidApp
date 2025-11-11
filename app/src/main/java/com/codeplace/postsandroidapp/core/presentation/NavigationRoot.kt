package com.codeplace.postsandroidapp.core.presentation


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.codeplace.postsandroidapp.core.presentation.components.StandardBottomAppBar
import com.codeplace.postsandroidapp.core.presentation.util.BottomNavigation
import com.codeplace.postsandroidapp.core.presentation.util.ScreenRoutes
import com.codeplace.postsandroidapp.feature_explore.presentation.comments.CommentsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.posts.ExplorePostsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.search.SearchScreenRoot
import com.codeplace.postsandroidapp.feature_favorites.presentation.FavoritesScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.SettingsScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.theme.ThemeScreenRoot


@Composable
fun NavigationRoot(
    themeViewModel: ThemeViewModel

) {


    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
                ?: BottomNavigation.EXPLORE.route::class.qualifiedName.orEmpty()

            val isBottomAppBarVisible = when (currentRoute) {
                BottomNavigation.EXPLORE.route::class.qualifiedName.orEmpty(),
                BottomNavigation.FAVORITES.route::class.qualifiedName.orEmpty(),
                BottomNavigation.SETTINGS.route::class.qualifiedName.orEmpty(),
                    -> true

                else -> false
            }
            StandardBottomAppBar(
                isBottomAppBarVisible = isBottomAppBarVisible,
                navController = navController
            )

        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeGraph,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation<ScreenRoutes.HomeGraph>(
                startDestination = ScreenRoutes.Explore
            ) {
                composable<ScreenRoutes.Explore> {
                    ExplorePostsScreenRoot(
                        onCardClick = { postId ->
                            navController.navigate(ScreenRoutes.Comments(postId = postId))
                        }
                    )
                }
                composable<ScreenRoutes.Comments> { backStackEntry ->
                    val commentsRoute: ScreenRoutes.Comments = backStackEntry.toRoute()
                    // StandardScaffold()
                    CommentsScreenRoot(
                        onBackAction = {
                            navController.popBackStack()
                            navController.navigate(ScreenRoutes.Explore)
                        }
                    )
                }
                composable<ScreenRoutes.Search> {
                    SearchScreenRoot()
                }

                composable<ScreenRoutes.Favorites> {
                    FavoritesScreenRoot()
                }

                composable<ScreenRoutes.Settings> {
                    SettingsScreenRoot(
                        onThemeClick = {
                            navController.navigate(ScreenRoutes.Theme)
                        }
                    )
                }
                composable<ScreenRoutes.Theme> {
                    ThemeScreenRoot(
                        themeViewModel = themeViewModel
                    )
                }

            }
        }
    }

}


