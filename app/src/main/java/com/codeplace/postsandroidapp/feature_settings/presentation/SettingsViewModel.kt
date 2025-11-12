package com.codeplace.postsandroidapp.feature_settings.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.postsandroidapp.PostsAndroidApp
import com.codeplace.postsandroidapp.core.domain.onError
import com.codeplace.postsandroidapp.core.domain.onSuccess
import com.codeplace.postsandroidapp.core.presentation.util.UiText
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.GetAppThemeUseCase
import com.codeplace.postsandroidapp.feature_settings.domain.use_case.SaveAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveAppThemeUseCase: SaveAppThemeUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val postsAndroidApp: PostsAndroidApp
) : ViewModel() {

    private var _appTheme = MutableStateFlow<AppTheme>(AppTheme.SYSTEM_MODE)
    val appTheme = _appTheme.asStateFlow()

    private var errorMessage by mutableStateOf<UiText>(UiText.DynamicString(""))

    init {
        getAppTheme()
    }


    fun changeAppTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            postsAndroidApp.appTheme.value = appTheme
            _appTheme.update { appTheme }
        saveAppTheme(appTheme)
        }
    }

    private fun getAppTheme() = viewModelScope.launch {
        getAppThemeUseCase.invoke()
            .onSuccess { savedAppTheme ->

                postsAndroidApp.appTheme.value = savedAppTheme
                _appTheme.update { savedAppTheme }

                Log.d("SettingsViewModel", "savedApp: $savedAppTheme")

            }
            .onError { error ->
                errorMessage = error.toUiText()
            }
    }

    private fun saveAppTheme(appTheme: AppTheme) = viewModelScope.launch {
        saveAppThemeUseCase.invoke(appTheme)
    }

}