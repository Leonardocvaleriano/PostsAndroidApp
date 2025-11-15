package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import DefaultSearchBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codeplace.postsandroidapp.core.presentation.DefaultLoadingScreen
import com.codeplace.postsandroidapp.core.presentation.screens.FeedBackCard
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.previews.mockPosts

@Preview(showBackground = true)
@Composable
fun PostsPreview() {
    PostsScreen(
        posts = mockPosts,
        onCardClick = {},
        commentsCount = 1,
        onSearchFinish = {},
        errorMessage = "Error"
    )

}

@Composable
fun ExplorePostsScreenRoot(
    exploreViewModel: ExploreViewModel,
    onCardClick: (postId: Int) -> Unit,
    onSearchFinish: () -> Unit,
    bottomPadding: Dp,
) {
    val isLoading by exploreViewModel.isLoading.collectAsState()
    val posts by exploreViewModel.posts.collectAsState()
    val errorMessage by exploreViewModel.errorMessage.collectAsState()

        if (isLoading) {
            DefaultLoadingScreen(modifier = Modifier.padding(bottom = bottomPadding))

        } else {
            PostsScreen(
                posts = posts,
                onCardClick = onCardClick,
                onSearchFinish = {
                    onSearchFinish()
                },
                errorMessage = errorMessage
            )
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    posts: List<Post>,
    onCardClick: (Int) -> Unit,
    onSearchFinish: () -> Unit,
    commentsCount: Int? = 0,
    errorMessage: String? = null
) {
    val textFieldState = rememberTextFieldState()

    val state = rememberLazyListState()
    val firstVisibleItem = state.firstVisibleItemIndex
    var showSearchBar by remember { mutableStateOf(true) }

    LaunchedEffect(state.firstVisibleItemIndex) {
        showSearchBar = firstVisibleItem < 1
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
            ) {
                AnimatedVisibility(
                    visible = showSearchBar,
                ) {
                    DefaultSearchBar(
                        textFieldState = textFieldState,
                        onSearch = { it },
                        searchResults = listOf("Result 1")
                    )
                }
            }
        }
    ) { padding ->

        LazyColumn(
            state = state,
            contentPadding = padding,

        ) {
            if (!errorMessage.isNullOrEmpty()){
                item {
                    FeedBackCard(
                        errorMessage = errorMessage,
                    )
                }

            }
            items(posts) { post ->
                PostCard(
                    post = post,
                    onCardClick = onCardClick,
                    containCommentCount = commentsCount
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}



