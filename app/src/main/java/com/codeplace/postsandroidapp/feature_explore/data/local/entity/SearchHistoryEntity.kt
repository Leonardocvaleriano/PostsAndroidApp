package com.codeplace.postsandroidapp.feature_explore.data.local.entity

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class SearchHistoryEntity(
    val recentPostSearches: List<String> = emptyList()
)