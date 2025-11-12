package com.codeplace.postsandroidapp.feature_settings.presentation

import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.domain.LocalStorageError
import com.codeplace.postsandroidapp.core.domain.LocalStorageError.*
import com.codeplace.postsandroidapp.core.presentation.util.UiText

fun LocalStorageError.toUiText(): UiText {
    val stringRes = when (this) {
        SERIALIZATION_ERROR -> R.string.an_unexpected_error_has_occurred
        IO_ERROR -> R.string.io_error
        CORRUPTION_ERROR -> R.string.corruption_error
        KEY_NOT_FOUND -> R.string.key_not_found
        UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResourceId(stringRes)
}