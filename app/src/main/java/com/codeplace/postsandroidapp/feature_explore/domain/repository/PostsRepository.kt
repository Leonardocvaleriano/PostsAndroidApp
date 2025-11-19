package com.codeplace.postsandroidapp.feature_explore.domain.repository

import com.codeplace.postsandroidapp.core.domain.DataError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.domain.models.SearchHistory
import kotlinx.collections.immutable.PersistentList

interface PostsRepository {
    suspend fun getPosts(): Result<List<Post>, DataError.Network>
    suspend fun getPost(postId: Int): Result<Post, DataError.Network>
    suspend fun getComments(postId: Int): Result<List<Comment>, DataError.Network>

    suspend fun saveRecentPostSearches(searchHistory: SearchHistory)

    suspend fun getRecentPostSearches(): Result<SearchHistory, DataError.Local>
}