package com.codeplace.postsandroidapp.core

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.core.presentation.NavigationRoot
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.example.compose.PostsAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var app: PostsAndroidApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentCompose()
    }

    private fun ComponentActivity.setContentCompose() {
        enableEdgeToEdge()
        setContent {


            PostsAndroidAppTheme(
                darkTheme = when (app.appTheme.value) {
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