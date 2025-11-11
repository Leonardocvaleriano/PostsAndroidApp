package com.codeplace.postsandroidapp.feature_settings.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.ThemeViewModel
import com.codeplace.postsandroidapp.core.presentation.components.TopAppBarBackArrow
import com.codeplace.postsandroidapp.feature_settings.presentation.domain.AppTheme
import com.example.compose.PostsAndroidAppTheme


@Preview(showBackground = true)
@Composable

fun ThemeScreenPreview() {
    PostsAndroidAppTheme {
        ThemeScreen(
            appTheme = AppTheme.LIGHT_MODE,
            onItemClick = { }
        )
    }
}


@Composable
fun ThemeScreenRoot(
    themeViewModel: ThemeViewModel,
) {
    val appThemeUiState by themeViewModel.currentAppTheme.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBarBackArrow(title = stringResource(R.string.top_bar_title_theme))
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
                ThemeScreen(
                    appTheme = appThemeUiState,
                    onItemClick = { currentAppTheme ->
                        themeViewModel.changeAppTheme(currentAppTheme)

                    }
                )

            }
        }
}


@Composable
fun ThemeScreen(
    appTheme: AppTheme?,
    onItemClick: (AppTheme) -> Unit
) {


    Column {
        ListItem(
            modifier = Modifier.clickable{
                onItemClick(AppTheme.LIGHT_MODE)
            },
            headlineContent = {
                Text(stringResource(R.string.light))
            },
            trailingContent = {
                RadioButton(
                    selected = appTheme == AppTheme.LIGHT_MODE,
                    onClick = {
                    },
                )
            },
        )
        ListItem(
            modifier = Modifier.clickable{
                onItemClick(AppTheme.DARK_MODE)
            },
            headlineContent = {
                Text(stringResource(R.string.dark))
            },
            trailingContent = {
                RadioButton(
                    selected = appTheme == AppTheme.DARK_MODE,
                    onClick = {
                     },
                )
            },
        )
        ListItem(
            modifier = Modifier.clickable{
                onItemClick(AppTheme.SYSTEM_MODE)
            },
            headlineContent = {
                Text(stringResource(R.string.system))
            },
            trailingContent = {
                RadioButton(
                    selected = appTheme == AppTheme.SYSTEM_MODE,
                    onClick = {
                    },
                )
            },
        )
    }
}




