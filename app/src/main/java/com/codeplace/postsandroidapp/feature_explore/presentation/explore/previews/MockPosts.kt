package com.codeplace.postsandroidapp.feature_explore.presentation.explore.previews

import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

val mockPosts = listOf<Post>(
    Post(
        id = 1,
        title = "Title",
        body = "Body",
        userId = 1,
    ),
    Post(
        id = 2,
        title = "Title 2 ",
        body = "Body 2",
        userId = 2,
    )
)