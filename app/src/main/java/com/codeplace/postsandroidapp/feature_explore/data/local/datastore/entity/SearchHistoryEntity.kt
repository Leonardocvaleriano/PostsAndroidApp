package com.codeplace.postsandroidapp.feature_explore.data.local.datastore.entity

import kotlinx.serialization.Serializable

@Serializable
data class SearchHistoryEntity(
    val recentPostSearches: List<String> = emptyList()
)