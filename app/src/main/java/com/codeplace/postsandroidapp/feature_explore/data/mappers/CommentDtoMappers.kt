package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.CommentDto
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment

fun List<CommentDto>.toDomain(): List<Comment> {
    return map { commentDto ->
        Comment(
            postId = commentDto.postId,
            id = commentDto.id,
            name = commentDto.name,
            email = commentDto.email,
            body = commentDto.body
        )
    }
}