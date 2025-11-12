package com.codeplace.postsandroidapp.feature_settings.domain

import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_settings.data.local.entities.AppThemeEntity

interface SettingsRepository {
    suspend fun saveAppTheme(appTheme: AppTheme): Unit
    suspend fun getAppTheme(): Result<AppTheme, LocalStorageError>
}