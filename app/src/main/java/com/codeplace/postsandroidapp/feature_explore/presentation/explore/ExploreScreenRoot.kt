package com.codeplace.postsandroidapp.feature_explore.presentation.explore

import DefaultSearchBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codeplace.postsandroidapp.core.presentation.DefaultLoadingScreen
import com.codeplace.postsandroidapp.core.presentation.screens.FeedBackCard
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.NoResultFoundContent
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import kotlinx.coroutines.launch

@Composable
fun ExplorePostsScreenRoot(
    exploreViewModel: ExploreViewModel,
    onCardClick: (postId: Int) -> Unit,
    onSearch: (String) -> Unit = { exploreViewModel.onSearch(it) },
    onSearchBarClick: () -> Unit = { exploreViewModel.loadRecentPostSearches() },
    bottomPadding: Dp,
    onRecentSearchItemClick: (String) -> Unit = { exploreViewModel.onSearch(it) },
    onGoBackClick: () -> Unit = { exploreViewModel.loadAllPosts() },
    onFavoriteClick: (Post) -> Unit = { exploreViewModel.updateFavouritePostState(it) },
) {


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val isSearchContentLoading by exploreViewModel.isSearchContentLoading.collectAsState()
    val isLoading by exploreViewModel.isLoading.collectAsState()
    val posts by exploreViewModel.allPosts.collectAsState()
    val errorMessage by exploreViewModel.errorMessage.collectAsState()
    val recentWordSearches by exploreViewModel.recentSearches.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        exploreViewModel.uiEvent.collect { event ->
            when(event){
                is ExploreUiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.messsage.asString(context))
                    }
                }
            }
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.padding(bottom = bottomPadding),
    ) { padding ->
        if (isLoading) {
            DefaultLoadingScreen(
                modifier = Modifier.padding(paddingValues = padding)
            )
        } else {
            PostsScreen(
                posts = posts,
                errorMessage = errorMessage.asString(),
                recentWordSearches = recentWordSearches,
                isSearchLoading = isSearchContentLoading,
                onCardClick = onCardClick,
                onFavoriteClick = onFavoriteClick,
                onSearch = onSearch,
                onSearchBarClick = onSearchBarClick,
                onRecentSearchItemClick = onRecentSearchItemClick,
                onGoBackClick = onGoBackClick,

            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    errorMessage: String?,
    recentWordSearches: List<String>,
    isSearchLoading: Boolean,
    onCardClick: (Int) -> Unit,
    onSearch: (String) -> Unit,
    onSearchBarClick: () -> Unit,
    onRecentSearchItemClick: (String) -> Unit,
    onGoBackClick: () -> Unit = {},
    onFavoriteClick: (Post) -> Unit,
) {

    val textFieldState = rememberTextFieldState()
    val listState = rememberLazyListState()

    val showSearchBar by remember {
        derivedStateOf { listState.firstVisibleItemScrollOffset < 30 }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {

        AnimatedVisibility(visible = showSearchBar) {
            DefaultSearchBar(
                textFieldState = textFieldState,
                searchResults = recentWordSearches,
                isLoading = isSearchLoading,
                onSearch = onSearch,
                onSearchBarClick = onSearchBarClick,
                onRecentSearchItemClick = onRecentSearchItemClick
            )
        }

        if (posts.isEmpty()) {
            NoResultFoundContent(onGoBackClick = onGoBackClick)
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {

                if (!errorMessage.isNullOrEmpty()) {
                    item {
                        Spacer(Modifier.size(12.dp))
                        FeedBackCard(errorMessage)
                        Spacer(Modifier.size(12.dp))
                    }
                }

                items(posts) { post ->
                    PostCard(
                        post = post,
                        onCardClick = onCardClick,
                        onFavoriteClick = { post ->
                            onFavoriteClick(post)
                        } ,
                    )
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}



