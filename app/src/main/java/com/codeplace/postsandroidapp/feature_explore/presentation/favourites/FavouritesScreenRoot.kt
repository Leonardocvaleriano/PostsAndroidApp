package com.codeplace.postsandroidapp.feature_explore.presentation.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.codeplace.postsandroidapp.feature_explore.presentation.favourites.preview.mockFavoritePosts
import com.example.compose.PostsAndroidAppTheme

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {

    PostsAndroidAppTheme {
        FavoritesScreen(
            favoritePosts = mockFavoritePosts,
            errorMessage = "Error message",
            bottomBarPadding = 0.dp,
            onFavoriteClick = {}
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: FavouritesViewModel = hiltViewModel<FavouritesViewModel>(),
    bottomBarPadding: Dp
) {

    val error by viewModel.error.collectAsStateWithLifecycle()
    val favoritePosts by viewModel.favoritePosts.collectAsStateWithLifecycle()


    FavoritesScreen(
        favoritePosts = favoritePosts,
        errorMessage = error?.asString(),
        bottomBarPadding = bottomBarPadding,
        onFavoriteClick = { post ->
            viewModel.deleteFavouritePost(post)
        }

    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritePosts: List<Post>,
    errorMessage: String?,
    bottomBarPadding: Dp,
    onFavoriteClick:(post:Post) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(top = 32.dp, bottom = bottomBarPadding)
    ) {
        item {
            DefaultTopAppBar(
                content = {
                    Text(
                        style = MaterialTheme.typography.headlineMedium,
                       text = stringResource(R.string.Favourites)

                    )
                }
            )
        }
        items(items = favoritePosts) { favoritePost ->
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {


                PostCard(
                    post = favoritePost,
                    onCardClick = {

                    },
                    showFavoriteIcon = true,
                    onFavoriteClick = { post ->

                        onFavoriteClick(post)
                    },

                )
                Spacer(modifier.size(8.dp))

            }

        }

    }

}

