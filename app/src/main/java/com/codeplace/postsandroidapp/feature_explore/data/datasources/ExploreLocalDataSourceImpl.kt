package com.codeplace.postsandroidapp.feature_explore.data.datasources

import androidx.datastore.core.DataStore
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ExploreLocalDataSourceImpl(
    val dataStore: DataStore<SearchHistoryEntity>) :

    ExploreLocalDataSource {

    override suspend fun saveRecentPostSearches(searchHistory: SearchHistoryEntity) {
        dataStore.updateData { current ->
            current.copy(
                recentPostSearches = searchHistory.recentPostSearches
            )
        }
    }

    override suspend fun getRecentPostSearches(): Result<SearchHistoryEntity, DataError.Local> {
        return try {
            val result = dataStore.data.map { exploreSettings ->
                exploreSettings.recentPostSearches
            }.first()
            Result.Success(SearchHistoryEntity(recentPostSearches = result))
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)

        }
    }
}