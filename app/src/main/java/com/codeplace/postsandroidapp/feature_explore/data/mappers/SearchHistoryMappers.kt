package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import kotlinx.collections.immutable.persistentListOf

fun SearchHistoryEntity.toDomain(): SearchHistory =
    SearchHistory(
        recentPostSearches = recentPostSearches.toList()
    )

fun SearchHistory.toEntity(): SearchHistoryEntity =

    SearchHistoryEntity(
        recentPostSearches = persistentListOf<String>().addAll(recentPostSearches)
    )