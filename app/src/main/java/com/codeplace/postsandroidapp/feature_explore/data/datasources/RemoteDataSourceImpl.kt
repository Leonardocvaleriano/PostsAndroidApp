package com.codeplace.postsandroidapp.feature_explore.data.datasources

import android.util.Log
import com.codeplace.postsandroidapp.core.data.network.safeApiCall
import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.network.HttpRoutes
import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.CommentDto
import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteDataSourceImpl(val httpClient: HttpClient) : RemoteDataSource {
    override suspend fun fetchPosts(): Result<List<PostDto>, DataError.Network> {
        return safeApiCall {
            httpClient.get(HttpRoutes.fetchPosts())
        }
    }

    override suspend fun fetchPost(postId: Int): Result<PostDto, DataError.Network> {
        return safeApiCall {
            httpClient.get(HttpRoutes.fetchPost(postId))
        }
    }

    override suspend fun fetchComments(postId: Int): Result<List<CommentDto>, DataError.Network> {
        return safeApiCall {
            httpClient.get (HttpRoutes.fetchComments(postId))
        }
    }
}