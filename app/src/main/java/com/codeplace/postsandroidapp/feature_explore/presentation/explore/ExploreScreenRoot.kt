package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import DefaultSearchBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codeplace.postsandroidapp.core.presentation.DefaultLoadingScreen
import com.codeplace.postsandroidapp.core.presentation.screens.FeedBackCard
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.NoResultFoundContent
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.previews.mockPosts
import kotlin.math.exp

@Preview(showBackground = true)
@Composable
fun PostsPreview() {
    PostsScreen(
        posts = mockPosts,
        onCardClick = {},
        commentsCount = 1,
        onSearch = {},
        errorMessage = "Error",
        onSearchBarClick = { },
        recentWordSearches = listOf("Last word searched"),
        onRecentSearchItemClick = {}
    )

}

@Composable
fun ExplorePostsScreenRoot(
    exploreViewModel: ExploreViewModel,
    onCardClick: (postId: Int) -> Unit,
    onSearch: (String) -> Unit = { query ->
        exploreViewModel.onSearch(query)
    },
    onSearchBarClick: () -> Unit = {
        exploreViewModel.loadRecentPostSearches()
    },
    bottomPadding: Dp,
    onRecentSearchItemClick:(String) -> Unit = { query ->
        exploreViewModel.onSearch(query)
    }
) {

    val isSearchContentLoading by exploreViewModel.isSearchContentLoading.collectAsState()
    val isLoading by exploreViewModel.isLoading.collectAsState()
    val posts by exploreViewModel.posts.collectAsState()
    val errorMessage by exploreViewModel.errorMessage.collectAsState()
    val recentWordSearches by exploreViewModel.recentSearches.collectAsState()

    if (isLoading) {
        DefaultLoadingScreen(modifier = Modifier.padding(bottom = bottomPadding))

    } else {
        PostsScreen(
            posts = posts,
            onCardClick = onCardClick,
            onSearch = onSearch,
            errorMessage = errorMessage,
            recentWordSearches = recentWordSearches,
            onSearchBarClick = {
                onSearchBarClick()
            },
            isLoading = isSearchContentLoading,
            onRecentSearchItemClick = { query ->
                onRecentSearchItemClick(query)
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    posts: List<Post>,
    onCardClick: (Int) -> Unit,
    onSearch: (String) -> Unit,
    onSearchBarClick:() -> Unit,
    commentsCount: Int? = 0,
    errorMessage: String? = null,
    recentWordSearches: List<String>,
    isLoading: Boolean = false,
    onRecentSearchItemClick:(String) -> Unit
) {
    val textFieldState = rememberTextFieldState()
    val state = rememberLazyListState()

    val showSearchBar by remember {
        derivedStateOf {
            if (state.firstVisibleItemScrollOffset >= 30) false else true
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        topBar = {
            Column(
                modifier = Modifier
            ) {
                AnimatedVisibility(
                    visible = showSearchBar,
                ) {
                    DefaultSearchBar(
                        textFieldState = textFieldState,
                        onSearch = { query ->
                            onSearch(query)
                        },
                        searchResults = recentWordSearches,
                        onSearchBarClick = {
                            onSearchBarClick()
                        },
                        isLoading =  isLoading,
                        onRecentSearchItemClick = { query ->
                            onRecentSearchItemClick(query)
                        }
                    )
                }
            }
        }
    ) { padding ->
        if (posts.isEmpty()) {
            NoResultFoundContent(modifier = Modifier.padding(padding))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state,
            contentPadding = padding,

            ) {

            if (!errorMessage.isNullOrEmpty()) {
                item {
                    Spacer(modifier = Modifier.size(12.dp))
                    FeedBackCard(
                        errorMessage = errorMessage,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
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



