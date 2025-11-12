package com.codeplace.postsandroidapp.core.presentation.navigation


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.codeplace.postsandroidapp.core.presentation.components.DefaultBottomAppBar
import com.codeplace.postsandroidapp.feature_explore.presentation.comments.CommentsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.posts.ExplorePostsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.posts.ExplorePostsViewModel
import com.codeplace.postsandroidapp.feature_favorites.presentation.FavoritesScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.SettingsViewModel
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.SettingsScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.ThemeScreenRoot


@Composable
fun NavigationRoot() {
    val navController = rememberNavController()


    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            DefaultBottomAppBar(
                isBottomAppBarVisible = true,
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
                    val explorePostsViewModel: ExplorePostsViewModel = hiltViewModel()

                    ExplorePostsScreenRoot(
                        explorePostsViewModel = explorePostsViewModel,
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
                    val settingsViewModel: SettingsViewModel = hiltViewModel()
                    val appThemeUiState by settingsViewModel.appTheme.collectAsStateWithLifecycle()

                    ThemeScreenRoot(
                        settingsViewModel = settingsViewModel,
                        currentAppTheme = appThemeUiState
                    )
                }

            }
        }
    }

}


