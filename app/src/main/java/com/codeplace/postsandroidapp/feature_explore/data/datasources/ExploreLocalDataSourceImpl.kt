package com.codeplace.postsandroidapp.feature_explore.data.datasources

import android.util.Log
import androidx.datastore.core.DataStore
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.local.datastore.entity.SearchHistoryEntity
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostDao
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ExploreLocalDataSourceImpl(
    val dataStore: DataStore<SearchHistoryEntity>,
    val postDao: PostDao
) :

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

    override suspend fun savePost(post: PostEntity): Result<Unit, DataError.Local> {
        return try {
            postDao.insert(postEntity = post)
            Result.Success(Unit)
        } catch (e: Exception){
            Result.Error(DataError.Local.UNKNOWN)
        }

    }

    override suspend fun getSavedPosts(): Result<List<PostEntity>, DataError.Local> {
        return try {
            val posts = postDao.getAll()
            Result.Success(posts)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}