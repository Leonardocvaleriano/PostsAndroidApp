package com.codeplace.postsandroidapp.feature_settings.data.local.mappers

import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme.*


fun AppTheme.toDataString(): String {
    return when (this) {
        LIGHT_MODE -> LIGHT_MODE.localStorageValue
        DARK_MODE -> DARK_MODE.localStorageValue
        SYSTEM_MODE -> SYSTEM_MODE.localStorageValue
    }
}

fun String.toDomainAppTheme(): AppTheme? {
    return when (this) {
        LIGHT_MODE.localStorageValue -> LIGHT_MODE
        DARK_MODE.localStorageValue -> DARK_MODE
        SYSTEM_MODE.localStorageValue -> SYSTEM_MODE
        else -> SYSTEM_MODE
    }
}
