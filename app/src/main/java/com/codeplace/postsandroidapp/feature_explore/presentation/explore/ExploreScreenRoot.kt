package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.core.presentation.screens.ErrorMessageText
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post

@Preview(showBackground = true)
@Composable
fun PostsPreview() {
    val postList = listOf(
        Post(
            id = 1,
            title = "Title",
            body = "Body",
            userId = 1,
        ), Post(
            id = 1,
            title = "Title",
            body = "Body",
            userId = 1,
        )
    )
        PostsScreen(
            posts = postList,
            onCardClick = {},
            commentsCount = 1
        )

}

@Composable
fun ExplorePostsScreenRoot(
    exploreViewModel: ExploreViewModel,
    onCardClick: (postId: Int) -> Unit,
    onSearchIconClick: () -> Unit = {},
    onSendIconClick: () -> Unit = {},
    innerPaddings: PaddingValues
) {

    val isLoading by exploreViewModel.isLoading.collectAsState()
    val posts by exploreViewModel.posts.collectAsState()
    val errorMessage by exploreViewModel.errorMessage.collectAsState()


        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .fillMaxSize()
        ) {

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (errorMessage.isNotEmpty()) {
                ErrorMessageText(
                    errorMessage = errorMessage,
                )
            } else {
                PostsScreen(
                    posts = posts,
                    onCardClick = onCardClick
                )
            }
        }

}


@Composable
fun PostsScreen(
    posts: List<Post>,
    onCardClick: (Int) -> Unit,
    commentsCount: Int? = 0,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = SpacingSize.small, bottom = SpacingSize.small),
        verticalArrangement = Arrangement.spacedBy(space = SpacingSize.small),
    ) {
        item {

        }
        items(posts) { post ->
            PostCard(
                post = post,
                onCardClick = onCardClick,
                containCommentCount = commentsCount
            )
        }
    }
}


