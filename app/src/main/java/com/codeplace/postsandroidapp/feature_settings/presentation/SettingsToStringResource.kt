package com.codeplace.postsandroidapp.feature_settings.presentation

import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.LocalStorageError.*
import com.codeplace.postsandroidapp.core.presentation.util.UiText

fun DataError.Local.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResourceId(stringRes)
}