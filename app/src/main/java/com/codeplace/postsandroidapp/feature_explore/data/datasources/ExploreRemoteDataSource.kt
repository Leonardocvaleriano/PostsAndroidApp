package com.codeplace.postsandroidapp.feature_explore.data.datasources

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.CommentDto
import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.PostDto

interface ExploreRemoteDataSource {
    suspend fun fetchPosts(): Result<List<PostDto>, DataError.Network>
    suspend fun fetchComments(postId: Int): Result<List<CommentDto>, DataError.Network>

    suspend fun fetchPost(postId: Int): Result<PostDto, DataError.Network>

}