package com.codeplace.postsandroidapp.feature_explore.domain.repository

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

interface PostsRepository {
    suspend fun getPosts(): Result<List<Post>, DataError.Network>
    suspend fun getPost(postId: Int): Result<Post, DataError.Network>
    suspend fun getComments(postId: Int): Result<List<Comment>, DataError.Network>
}