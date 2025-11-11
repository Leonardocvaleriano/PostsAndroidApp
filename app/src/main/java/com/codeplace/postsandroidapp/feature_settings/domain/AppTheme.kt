package com.codeplace.postsandroidapp.feature_settings.domain

enum class AppTheme {
    LIGHT_MODE,
    DARK_MODE,
    SYSTEM_MODE;

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}