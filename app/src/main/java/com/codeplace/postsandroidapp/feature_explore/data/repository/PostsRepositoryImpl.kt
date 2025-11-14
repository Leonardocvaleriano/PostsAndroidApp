package com.codeplace.postsandroidapp.feature_explore.data.repository

import android.util.Log
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.core.domain.map
import com.codeplace.postsandroidapp.feature_explore.data.datasources.RemoteDataSource
import com.codeplace.postsandroidapp.feature_explore.data.mappers.toDomain
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : PostsRepository {

    override suspend fun getPosts(): Result<List<Post>, DataError.Network> {
        return remoteDataSource.fetchPosts().map { postsDto -> postsDto.toDomain() }
    }

    override suspend fun getComments(postId: Int): Result<List<Comment>, DataError.Network> {
        return remoteDataSource.fetchComments(postId).map { commentsDto -> commentsDto.toDomain() }
    }

    override suspend fun getPost(postId: Int): Result<Post, DataError.Network> {

        return remoteDataSource.fetchPost(postId).map { postDto ->
            postDto.toDomain() }
    }
}