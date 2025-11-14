package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.network.dtos.PostDto
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

fun List<PostDto>.toDomain(): List<Post> {
    return map { dto ->
        Post(
            id = dto.id,
            title = dto.title,
            body = dto.body,
            userId = dto.userId
        )
    }

}