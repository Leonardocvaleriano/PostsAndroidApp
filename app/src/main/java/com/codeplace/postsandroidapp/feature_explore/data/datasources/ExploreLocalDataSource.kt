package com.codeplace.postsandroidapp.feature_explore.data.datasources

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import kotlinx.collections.immutable.PersistentList

interface ExploreLocalDataSource {
    suspend fun saveRecentPostSearches(searchHistory: SearchHistoryEntity)
    suspend fun getRecentPostSearches(): Result<SearchHistoryEntity, DataError.Local>
}