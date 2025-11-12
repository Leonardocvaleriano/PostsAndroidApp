package com.codeplace.postsandroidapp.feature_settings.data.local.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_settings.data.local.entities.AppThemeEntity
import com.codeplace.postsandroidapp.feature_settings.data.local.util.Constants
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class LocalPrefDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : LocalPrefDataSource {
    override suspend fun saveAppTheme(appTheme: AppThemeEntity) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(Constants.KEY_THEME)] = appTheme.theme ?: ""
        }
    }

    override suspend fun getAppTheme(): Result<String?, LocalStorageError> {
        return try {
            val result = dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(Constants.KEY_THEME)] ?: ""
            }.firstOrNull()

            Result.Success(result)
        } catch (e: Exception) {
//            Log.e(tag = "LocalPrefDataSourceImpl" , msg = "${e.message}")
            Result.Error(LocalStorageError.UNKNOWN)
        }
    }
}