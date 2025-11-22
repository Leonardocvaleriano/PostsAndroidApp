package com.codeplace.postsandroidapp.feature_explore.data.repository

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.core.domain.map
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreLocalDataSource
import com.codeplace.postsandroidapp.feature_explore.data.datasources.ExploreRemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.mappers.toDomain
import com.codeplace.postsandroidapp.feature_explore.data.mappers.toEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import com.codeplace.postsandroidapp.feature_explore.domain.repository.ExploreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ExploreRepositoryImpl(
    private val exploreRemoteDataSource: ExploreRemoteDataSource,
    private val exploreLocalDataSource: ExploreLocalDataSource
) : ExploreRepository {

    override suspend fun getPosts(): Result<List<Post>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            exploreRemoteDataSource.fetchPosts().map { postsDto -> postsDto.toDomain() }
        }
    }

    override suspend fun getComments(postId: Int): Result<List<Comment>, DataError.Network> {
        return withContext(Dispatchers.IO) {
            exploreRemoteDataSource.fetchComments(postId)
                .map { commentsDto -> commentsDto.toDomain() }
        }
    }

    override suspend fun getPost(postId: Int): Result<Post, DataError.Network> {
        return withContext(Dispatchers.IO){
            exploreRemoteDataSource.fetchPost(postId).map { postDto ->
                postDto.toDomain()
        }
        }
    }

    override suspend fun saveRecentPostSearches(searchHistory: SearchHistory) {
        return  withContext(Dispatchers.IO) {
            exploreLocalDataSource.saveRecentPostSearches(
                searchHistory = searchHistory.toEntity()
            )
        }
    }

    override suspend fun getRecentPostSearches(): Result<SearchHistory, DataError.Local> {
        return withContext(Dispatchers.IO){
            exploreLocalDataSource.getRecentPostSearches().map { it.toDomain() }
        }
    }

    override suspend fun saveFavouritePost(post: Post): Result<Unit, DataError.Local> {
        return withContext(Dispatchers.IO){
            exploreLocalDataSource.saveFavouritePost(post = post.toEntity())
        }
    }

    override suspend fun getFavouritePosts(): Result<Flow<List<Post>>, DataError.Local> {
        return withContext(Dispatchers.IO){
            exploreLocalDataSource.getSavedFavourites().map { it.map { savedFavouritePosts -> savedFavouritePosts.toDomain() } }
        }
    }

    override suspend fun deleteFavouritePost(post: Post): Result<Unit, DataError.Local> {
        return withContext(Dispatchers.IO){
            exploreLocalDataSource.deleteFavouritePost(post = post.toEntity())
        }
    }
}