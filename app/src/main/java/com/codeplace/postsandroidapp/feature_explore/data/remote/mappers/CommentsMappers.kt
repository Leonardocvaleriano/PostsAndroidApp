package com.codeplace.postsandroidapp.feature_explore.data.remote.mappers

import com.codeplace.postsandroidapp.feature_explore.data.remote.dto.CommentDto
import com.codeplace.postsandroidapp.feature_explore.domain.model.Comment

fun CommentDto.toDomain(): Comment {
    return Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body
    )
}