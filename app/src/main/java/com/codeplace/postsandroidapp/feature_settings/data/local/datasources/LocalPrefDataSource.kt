package com.codeplace.postsandroidapp.feature_settings.data.local.datasources

import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_settings.data.local.entities.AppThemeEntity

sealed interface LocalPrefDataSource {
    suspend fun saveAppTheme(appTheme: AppThemeEntity)
    suspend fun getAppTheme(): Result<String?, LocalStorageError>
}