package com.codeplace.postsandroidapp.feature_settings.data.local.mappers

import com.codeplace.postsandroidapp.feature_settings.data.local.entities.AppThemeEntity
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme.*

fun AppTheme.toAppThemeEntity(): AppThemeEntity {

    val theme = when (this) {
        LIGHT_MODE -> this.localStorageValue
        DARK_MODE -> this.localStorageValue
        SYSTEM_MODE -> this.localStorageValue
    }
    return AppThemeEntity(
        theme = theme
    )
}

fun String.toDomainAppTheme(): AppTheme {
  return when(this){
        LIGHT_MODE.localStorageValue -> LIGHT_MODE
        DARK_MODE.localStorageValue -> DARK_MODE
        SYSTEM_MODE.localStorageValue -> SYSTEM_MODE
        else -> SYSTEM_MODE
    }
}
