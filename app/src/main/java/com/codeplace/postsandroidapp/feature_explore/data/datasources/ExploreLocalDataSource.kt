package com.codeplace.postsandroidapp.feature_explore.data.datasources

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.local.datastore.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostEntity
import kotlinx.coroutines.flow.Flow


interface ExploreLocalDataSource {
    suspend fun saveRecentPostSearches(searchHistory: SearchHistoryEntity)
    suspend fun getRecentPostSearches(): Result<SearchHistoryEntity, DataError.Local>

    suspend fun saveFavouritePost(post: PostEntity): Result<Unit, DataError.Local>
    suspend fun getSavedFavourites(): Result<Flow<List<PostEntity>>, DataError.Local>

    suspend fun deleteFavouritePost(post: PostEntity): Result<Unit, DataError.Local>
}