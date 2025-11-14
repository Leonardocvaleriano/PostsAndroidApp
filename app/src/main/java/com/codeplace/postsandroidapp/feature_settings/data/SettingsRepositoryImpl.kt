package com.codeplace.postsandroidapp.feature_settings.data
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.core.domain.map
import com.codeplace.postsandroidapp.feature_settings.data.datasources.LocalDataSource
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toDataString
import com.codeplace.postsandroidapp.feature_settings.data.local.mappers.toDomainAppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.SettingsRepository

class SettingsRepositoryImpl(
    private val localDataSource: LocalDataSource
) : SettingsRepository {
    override suspend fun saveAppTheme(appTheme: AppTheme) {
        localDataSource.saveAppTheme(appTheme.toDataString())
    }

    override suspend fun getAppTheme(): Result<AppTheme, DataError.Local> {
        return localDataSource.getAppTheme()
            .map { appTheme -> appTheme.toDomainAppTheme() ?: AppTheme.SYSTEM_MODE }
    }
}