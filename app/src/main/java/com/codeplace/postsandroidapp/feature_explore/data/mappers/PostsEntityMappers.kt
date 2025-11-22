package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

fun List<PostEntity>.toDomain(): List<Post> {
    return map { entity ->
        Post(
            id = entity.id ?: 0,
            title = entity.title ?: "",
            body = entity.body ?: "",
            userId = entity.userId ?: 0
        )
    }
}