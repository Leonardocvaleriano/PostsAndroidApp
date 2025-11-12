package com.codeplace.postsandroidapp.feature_explore.data.remote.mappers

import com.codeplace.postsandroidapp.feature_explore.data.remote.dto.PostDto
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}