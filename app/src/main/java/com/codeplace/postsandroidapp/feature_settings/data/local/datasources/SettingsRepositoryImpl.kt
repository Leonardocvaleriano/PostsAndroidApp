package com.codeplace.postsandroidapp.feature_settings.data.local.datasources

import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.core.domain.map
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toAppThemeEntity
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toDomainAppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository

class SettingsRepositoryImpl(
    private val localPrefDataSource: LocalPrefDataSource
): SettingsRepository {
    override suspend fun saveAppTheme(appTheme: AppTheme) {
        localPrefDataSource.saveAppTheme(appTheme.toAppThemeEntity())
    }

    override suspend fun getAppTheme(): Result<AppTheme, LocalStorageError> {
           return localPrefDataSource.getAppTheme().map { it?.toDomainAppTheme()?: AppTheme.SYSTEM_MODE }
    }

}