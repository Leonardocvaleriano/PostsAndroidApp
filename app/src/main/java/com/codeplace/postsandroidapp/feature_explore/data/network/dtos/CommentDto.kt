package com.codeplace.postsandroidapp.feature_explore.data.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)
