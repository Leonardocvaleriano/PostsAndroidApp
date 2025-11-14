package com.codeplace.postsandroidapp.feature_settings.domain

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result

interface SettingsRepository {
    suspend fun saveAppTheme(appTheme: AppTheme): Unit
    suspend fun getAppTheme(): Result<AppTheme, DataError.Local>
}