package com.codeplace.postsandroidapp.feature_settings.presentation

import androidx.lifecycle.ViewModel
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val application: PostsAndroidApp
) : ViewModel() {

    private var _appTheme =  MutableStateFlow<AppTheme>(AppTheme.SYSTEM_MODE)
    val appTheme = _appTheme.asStateFlow()

    fun changeAppTheme(currentAppTheme: AppTheme) {

        when(currentAppTheme){
            AppTheme.LIGHT_MODE -> {
                application.appTheme.value = currentAppTheme
                _appTheme.value = application.appTheme.value

            }
            AppTheme.DARK_MODE -> {
                application.appTheme.value = currentAppTheme
                _appTheme.value = application.appTheme.value

            }
            AppTheme.SYSTEM_MODE -> {
                application.appTheme.value = currentAppTheme
                _appTheme.value = application.appTheme.value

            }
        }

    }

}