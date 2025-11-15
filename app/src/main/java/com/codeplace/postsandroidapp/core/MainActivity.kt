package com.codeplace.postsandroidapp.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.core.presentation.navigation.NavigationRoot
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.theme.ThemeViewModel
import com.example.compose.PostsAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.runtime.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var postsAndroidApp: PostsAndroidApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentCompose()
    }



    private fun ComponentActivity.setContentCompose() {
        enableEdgeToEdge()
        setContent {

            val themeViewModel: ThemeViewModel = hiltViewModel()

            themeViewModel.appTheme.collectAsState().value.let { appTheme ->
                PostsAndroidAppTheme(
                    darkTheme = when (postsAndroidApp.appTheme.value) {
                        AppTheme.LIGHT_MODE -> false
                        AppTheme.DARK_MODE -> true
                        AppTheme.SYSTEM_MODE -> isSystemInDarkTheme()
                    }
                ) {
                    NavigationRoot()
                }

            }

        }
    }


}