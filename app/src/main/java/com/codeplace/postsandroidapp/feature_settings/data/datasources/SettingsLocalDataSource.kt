package com.codeplace.postsandroidapp.feature_settings.data.datasources

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result

sealed interface SettingsLocalDataSource {
    suspend fun saveAppTheme(appTheme: String)
    suspend fun getAppTheme(): Result<String, DataError.Local>
}