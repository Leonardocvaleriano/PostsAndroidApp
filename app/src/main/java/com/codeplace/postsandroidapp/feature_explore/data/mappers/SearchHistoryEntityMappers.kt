package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.local.datastore.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory

fun SearchHistoryEntity.toDomain(): SearchHistory =
    SearchHistory(
        recentPostSearches = recentPostSearches.toList()
    )

fun SearchHistory.toEntity(): SearchHistoryEntity =

    SearchHistoryEntity(
        recentPostSearches = recentPostSearches
    )