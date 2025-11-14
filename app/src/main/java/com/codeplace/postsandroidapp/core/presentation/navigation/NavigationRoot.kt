package com.codeplace.postsandroidapp.core.presentation.navigation


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.codeplace.postsandroidapp.core.presentation.components.DefaultBottomAppBar
import com.codeplace.postsandroidapp.feature_explore.presentation.comments.CommentsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.ExplorePostsScreenRoot
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.ExploreViewModel
import com.codeplace.postsandroidapp.feature_favorites.presentation.FavoritesScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.SettingsViewModel
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.SettingsScreenRoot
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.ThemeScreenRoot


@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val screenRoutes = backStackEntry?.destination?.route


    val isBottomAppBarVisible = when (screenRoutes) {

        ScreenRoutes.Explore::class.qualifiedName -> true
        ScreenRoutes.Favorites::class.qualifiedName -> true
        ScreenRoutes.Settings::class.qualifiedName -> true
        else -> false
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
                AnimatedVisibility(
                    visible = isBottomAppBarVisible,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                ){
                    DefaultBottomAppBar(
                        navController = navController
                    )

            }


        }

    ) {  innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.HomeGraph,
        ) {
            navigation<ScreenRoutes.HomeGraph>(
                startDestination = ScreenRoutes.Explore
            ) {

                composable<ScreenRoutes.Explore> {
                    val exploreViewModel: ExploreViewModel = hiltViewModel()

                    ExplorePostsScreenRoot(
                        exploreViewModel = exploreViewModel,
                        onCardClick = { postId ->
                            navController.navigate(ScreenRoutes.Comments(postId = postId))
                        },
                        innerPaddings = innerPaddings

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


