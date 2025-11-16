package com.codeplace.postsandroidapp.feature_favorites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreenRoot(
    modifier: Modifier = Modifier,
    onSendIconClick: () -> Unit = {},
    onSearchIconClick: () -> Unit = {},
) {

    Box{
        FavoritesScreen()
    }
}
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreenRoot()
}
