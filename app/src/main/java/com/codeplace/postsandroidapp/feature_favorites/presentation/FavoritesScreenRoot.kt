package com.codeplace.postsandroidapp.feature_favorites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.components.PostCard
import com.codeplace.postsandroidapp.feature_favorites.presentation.preview.mockFavoritePosts
import com.codeplace.postsandroidapp.feature_settings.presentation.screens.settings.SettingsScreen
import com.example.compose.PostsAndroidAppTheme

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {

    PostsAndroidAppTheme {
        FavoritesScreen(
            favoritePosts = mockFavoritePosts,
            errorMessage = "Error message",
            bottomBarPadding = 0.dp
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel<FavoritesViewModel>(),
    bottomBarPadding: Dp
) {

    val error by viewModel.error.collectAsStateWithLifecycle()
    val favoritePosts by viewModel.favoritePosts.collectAsStateWithLifecycle()


    FavoritesScreen(
        favoritePosts = favoritePosts,
        errorMessage = error?.asString(),
        bottomBarPadding = bottomBarPadding
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritePosts: List<Post>,
    errorMessage: String?,
    bottomBarPadding: Dp
) {

    LazyColumn(
        contentPadding = PaddingValues(top = 32.dp, bottom = bottomBarPadding)
    ) {
        item {
            DefaultTopAppBar(
                content = {
                    Text(
                        style = MaterialTheme.typography.headlineMedium,
                       text = stringResource(R.string.title_favourites)

                    )
                }
            )
        }
        items(items = favoritePosts) { favoritePost ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {


                Spacer(modifier.size(8.dp))
                PostCard(
                    post = favoritePost,
                    onCardClick = {

                    },
                    showFavoriteIcon = true,
                    onFavoriteClick = {

                    }
                )
            }

        }

    }

}

