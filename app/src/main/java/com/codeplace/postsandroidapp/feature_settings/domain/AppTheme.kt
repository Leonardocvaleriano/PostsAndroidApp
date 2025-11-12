package com.codeplace.postsandroidapp.feature_settings.domain

enum class AppTheme(val localStorageValue: String) {
    LIGHT_MODE(localStorageValue = "light_mode"),
    DARK_MODE(localStorageValue = "dark_mode"),
    SYSTEM_MODE(localStorageValue = "system_mode");

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}