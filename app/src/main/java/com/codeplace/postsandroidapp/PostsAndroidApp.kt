package com.codeplace.postsandroidapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostsAndroidApp:Application(){
    val appTheme = mutableStateOf<AppTheme>(AppTheme.SYSTEM_MODE)

}
