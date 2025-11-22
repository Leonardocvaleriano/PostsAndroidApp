package com.codeplace.postsandroidapp.feature_explore.data.mappers

import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostEntity
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post



fun Post.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}


