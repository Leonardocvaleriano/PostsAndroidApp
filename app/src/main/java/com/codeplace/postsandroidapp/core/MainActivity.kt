package com.codeplace.postsandroidapp.core

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.codeplace.postsandroidapp.core.presentation.NavigationRoot
import com.codeplace.postsandroidapp.core.presentation.ThemeViewModel
import com.codeplace.postsandroidapp.feature_settings.presentation.domain.AppTheme
import com.example.compose.PostsAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentCompose()
    }

    private fun ComponentActivity.setContentCompose() {
        enableEdgeToEdge()
        setContent {
            val activity = LocalViewModelStoreOwner.current!!
            val themeViewModel: ThemeViewModel = hiltViewModel(activity)
            val currentAppThemeUiState by themeViewModel.currentAppTheme.collectAsStateWithLifecycle()
            LaunchedEffect(currentAppThemeUiState) {
                Log.d("MainActivity", "Theme changed to: $currentAppThemeUiState")
            }
            PostsAndroidAppTheme(
                darkTheme = when (currentAppThemeUiState) {
                    AppTheme.LIGHT_MODE -> false
                    AppTheme.DARK_MODE -> true
                    AppTheme.SYSTEM_MODE -> isSystemInDarkTheme()
                    null -> isSystemInDarkTheme()
                }
            ) {
                NavigationRoot(themeViewModel = themeViewModel)
            }
        }
    }

}