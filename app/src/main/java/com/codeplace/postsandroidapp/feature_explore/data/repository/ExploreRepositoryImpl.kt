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

class ExploreRepositoryImpl(
    private val exploreRemoteDataSource: ExploreRemoteDataSource,
    private val exploreLocalDataSource: ExploreLocalDataSource
) : ExploreRepository {

    override suspend fun getPosts(): Result<List<Post>, DataError.Network> {
        return exploreRemoteDataSource.fetchPosts().map { postsDto -> postsDto.toDomain() }
    }

    override suspend fun getComments(postId: Int): Result<List<Comment>, DataError.Network> {
        return exploreRemoteDataSource.fetchComments(postId)
            .map { commentsDto -> commentsDto.toDomain() }
    }

    override suspend fun getPost(postId: Int): Result<Post, DataError.Network> {

        return exploreRemoteDataSource.fetchPost(postId).map { postDto ->
            postDto.toDomain()
        }
    }

    override suspend fun saveRecentPostSearches(searchHistory: SearchHistory) {

        return exploreLocalDataSource.saveRecentPostSearches(
            searchHistory = searchHistory.toEntity()
        )
    }

    override suspend fun getRecentPostSearches(): Result<SearchHistory, DataError.Local> {
        return exploreLocalDataSource.getRecentPostSearches().map { it.toDomain() }
    }
}