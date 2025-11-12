package com.codeplace.postsandroidapp.feature_explore.domain.repository

import com.codeplace.postsandroidapp.core.domain.NetworkError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.model.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post

interface PostsRepository {

    suspend fun getPosts():Result<List<Post>, NetworkError>
    suspend fun getCommentsByPostId(postId:Int):Result<List<Comment>, NetworkError>
    suspend fun getPostByPostId(postId:Int):Result<Post, NetworkError>
}