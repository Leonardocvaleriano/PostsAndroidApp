package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import com.codeplace.postsandroidapp.core.presentation.util.UiText

sealed interface ExploreUiEvent {
    data class ShowSnackBar(val messsage: UiText): ExploreUiEvent
}