package com.codeplace.postsandroidapp.feature_settings.data
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.core.domain.map
import com.codeplace.postsandroidapp.feature_settings.data.datasources.SettingsLocalDataSource
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toDataString
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toDomainAppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {
    override suspend fun saveAppTheme(appTheme: AppTheme) {
        settingsLocalDataSource.saveAppTheme(appTheme.toDataString())
    }

    override suspend fun getAppTheme(): Result<AppTheme, DataError.Local> {
        return settingsLocalDataSource.getAppTheme()
            .map { appTheme -> appTheme.toDomainAppTheme() ?: AppTheme.SYSTEM_MODE }
    }
}