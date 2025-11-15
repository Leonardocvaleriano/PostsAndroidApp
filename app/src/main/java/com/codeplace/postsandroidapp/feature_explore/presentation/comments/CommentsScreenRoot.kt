package com.codeplace.postsandroidapp.feature_explore.presentation.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar
import com.codeplace.postsandroidapp.core.presentation.screens.FeedBackCard
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.codeplace.postsandroidapp.feature_explore.domain.models.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.comments.components.CommentCard
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.example.compose.PostsAndroidAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreenRoot(
    onBackAction: () -> Unit = {},
    viewModel: CommentsViewModel = hiltViewModel(),
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val comments by viewModel.commentsUiState.collectAsState()
    val post by viewModel.post.collectAsState()
    val errorPost by viewModel.errorPost.collectAsState()
    val errorComments by viewModel.errorComments.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            DefaultTopAppBar(
                onNavigationIconClick = {
                    onBackAction()
                },
                content = {
                    Text(
                        stringResource(R.string.title_comments)
                    )
                },
            )

        }
    ) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding )
            .fillMaxSize()
        ) {

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                CommentsScreen(
                    comments = comments,
                    post = post,
                    errorPost = errorPost,
                    errorComment = errorComments,
                )
            }


        }
    }
}


@Composable
fun CommentsScreen(
    modifier: Modifier = Modifier,
    post: Post?,
    comments: List<Comment>? = emptyList(),
    onCardClick: (Int) -> Unit = {},
    errorPost: String = "",
    errorComment: String = "",
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(SpacingSize.small)
    ) {
        if (errorPost.isNotEmpty()) {
            item {
                FeedBackCard(
                    errorMessage = errorPost
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface)
                )
                Text(
                    modifier = Modifier.padding(horizontal = SpacingSize.large),
                    text = stringResource(R.string.title_comments),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        } else {
            post?.let { post ->
                item {
                    PostCard(
                        post = post,
                        onCardClick = {}
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.surface)
                    )
                    Text(
                        modifier = Modifier.padding(
                            start = SpacingSize.large,
                            top = SpacingSize.large
                        ),
                        text = stringResource(R.string.title_comments),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }


        item {
            if (errorComment.isNotEmpty()) {
                FeedBackCard(errorMessage = errorComment)
            } else if (comments.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpacingSize.large,
                            vertical = SpacingSize.large
                        ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    text = stringResource(R.string.body_text_no_comments),

                    )
            }
        }

        items(comments!!) { comment ->
            Box(modifier = modifier.padding(horizontal = SpacingSize.large)) {
                CommentCard(
                    name = comment.name,
                    body = comment.body,
                    email = comment.email
                )

            }


        }


    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentsScreenPreview() {

    PostsAndroidAppTheme {
        CommentsScreen(
            comments = listOf(
                Comment(
                    postId = 1,
                    id = 1,
                    name = "name",
                    email = "email",
                    body = "body"
                ),
                Comment(
                    postId = 1,
                    id = 1,
                    name = "name",
                    email = "email",
                    body = "body"
                ),
                Comment(
                    postId = 1,
                    id = 1,
                    name = "name",
                    email = "email",
                    body = "body"
                )
            ),
            post = Post(
                userId = 1,
                id = 1,
                title = "title",
                body = "body"
            )
        )
    }

}




