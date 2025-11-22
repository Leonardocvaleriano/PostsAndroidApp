package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.PostDto
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
}