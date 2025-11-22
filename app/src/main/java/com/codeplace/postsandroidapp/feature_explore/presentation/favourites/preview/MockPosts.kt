package com.codeplace.postsandroidapp.feature_explore.presentation.favourites.preview

import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

val mockFavoritePosts = List(30) { index ->
    Post(
        id = index,
        title = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor in",
        body = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata",
        userId = 1,
    )
}