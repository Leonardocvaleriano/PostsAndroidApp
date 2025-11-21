package com.codeplace.postsandroidapp.feature_explore.data.datasources

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity

interface ExploreLocalDataSource {
    suspend fun saveRecentPostSearches(searchHistory: SearchHistoryEntity)
    suspend fun getRecentPostSearches(): Result<SearchHistoryEntity, DataError.Local>
}