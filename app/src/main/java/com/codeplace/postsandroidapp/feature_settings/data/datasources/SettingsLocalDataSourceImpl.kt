package com.codeplace.postsandroidapp.feature_settings.data.datasources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_settings.data.local.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingsLocalDataSource {
    override suspend fun saveAppTheme(appTheme: String) {
        dataStore.edit { preferences ->
            preferences[Constants.KEY_THEME] = appTheme
        }
    }

    override suspend fun getAppTheme(): Result<String, DataError.Local> {
        return try {
            val result = dataStore.data.map { preferences ->
                preferences[Constants.KEY_THEME] ?: ""
            }.first()
            Result.Success(data = result)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}