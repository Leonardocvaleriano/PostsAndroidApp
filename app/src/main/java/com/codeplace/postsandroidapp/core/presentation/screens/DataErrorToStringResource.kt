package com.codeplace.postsandroidapp.core.presentation.screens

import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.DataError.Network.CONFLICT
import com.codeplace.postsandroidapp.core.domain.DataError.Network.NO_INTERNET
import com.codeplace.postsandroidapp.core.domain.DataError.Network.PAYLOAD_TOO_LARGE
import com.codeplace.postsandroidapp.core.domain.DataError.Network.REQUEST_TIMEOUT
import com.codeplace.postsandroidapp.core.domain.DataError.Network.SERIALIZATION
import com.codeplace.postsandroidapp.core.domain.DataError.Network.SERVER_ERROR
import com.codeplace.postsandroidapp.core.domain.DataError.Network.TOO_MANY_REQUESTS
import com.codeplace.postsandroidapp.core.domain.DataError.Network.UNABLE_TO_CONNECT
import com.codeplace.postsandroidapp.core.domain.DataError.Network.UNAUTHORIZED
import com.codeplace.postsandroidapp.core.domain.DataError.Network.UNKNOWN
import com.codeplace.postsandroidapp.core.presentation.util.UiText

fun DataError.Local.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResourceId(stringRes)
}
fun DataError.Network.toUiText(): UiText {
    val stringRes = when (this) {
        REQUEST_TIMEOUT -> R.string.request_timed_out
        UNAUTHORIZED -> R.string.unauthorized
        CONFLICT -> R.string.conflict
        TOO_MANY_REQUESTS -> R.string.too_many_requests
        NO_INTERNET -> R.string.no_internet
        PAYLOAD_TOO_LARGE -> R.string.payload_too_large
        SERVER_ERROR -> R.string.server_error
        SERIALIZATION -> R.string.serialization
        UNABLE_TO_CONNECT -> R.string.unable_to_connect
        UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResourceId(stringRes)
}