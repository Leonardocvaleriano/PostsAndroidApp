package com.codeplace.postsandroidapp.core.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codeplace.postsandroidapp.feature_settings.presentation.domain.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
) : ViewModel() {

    private val _currentAppTheme = MutableStateFlow<AppTheme?>(AppTheme.SYSTEM_MODE)
    val currentAppTheme = _currentAppTheme.asStateFlow()



    fun changeAppTheme(currentAppTheme: AppTheme) {
        Log.d("ThemeViewModel", "currentAppTheme: _${_currentAppTheme.value})")

        when(currentAppTheme){
            AppTheme.LIGHT_MODE -> {
                _currentAppTheme.update {
                    AppTheme.LIGHT_MODE
                }

            }
            AppTheme.DARK_MODE -> {
                _currentAppTheme.update {
                    AppTheme.DARK_MODE
                }
            }
            AppTheme.SYSTEM_MODE -> {
                _currentAppTheme.update {
                    AppTheme.SYSTEM_MODE
                }

            }
        }

    }

}