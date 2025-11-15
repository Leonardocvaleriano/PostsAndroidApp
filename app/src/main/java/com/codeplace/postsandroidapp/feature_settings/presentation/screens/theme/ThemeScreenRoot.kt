package com.codeplace.postsandroidapp.feature_settings.presentation.screens.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar
import com.codeplace.postsandroidapp.feature_settings.domain.AppTheme
import com.example.compose.PostsAndroidAppTheme


@Preview(showBackground = true)
@Composable

fun ThemeScreenPreview() {
    PostsAndroidAppTheme {
        ThemeScreen(
            currentAppTheme = AppTheme.LIGHT_MODE,
            onItemClick = { }
        )
    }
}


@Composable
fun ThemeScreenRoot(
    themeViewModel: ThemeViewModel,
    currentAppTheme: AppTheme?,
    onBackClick: () -> Unit = {}

) {

        Column {
            ThemeScreen(
                    currentAppTheme = currentAppTheme,
                    onItemClick = { currentAppTheme ->
                        themeViewModel.changeAppTheme(currentAppTheme)

                    },
                onBackClick = {
                    onBackClick()
                }
                )

            }
        }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(
    currentAppTheme: AppTheme?,
    onItemClick: (AppTheme) -> Unit,
    onBackClick:() -> Unit = {}
) {

    Column {
        DefaultTopAppBar(
            onNavigationIconClick = {
              onBackClick()
            },
            content = {
                Text(
                    stringResource(R.string.top_bar_title_theme)
                )
            },
        )

        ListItem(
            modifier = Modifier.clickable{
                onItemClick(AppTheme.LIGHT_MODE)
            },
            headlineContent = {
                Text(stringResource(R.string.light))
            },
            trailingContent = {
                RadioButton(
                    selected = currentAppTheme == AppTheme.LIGHT_MODE,
                    onClick = {
                        onItemClick(AppTheme.LIGHT_MODE)

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
                    selected = currentAppTheme == AppTheme.DARK_MODE,
                    onClick = {
                        onItemClick(AppTheme.DARK_MODE)

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
                    selected = currentAppTheme == AppTheme.SYSTEM_MODE,
                    onClick = {
                        onItemClick(AppTheme.SYSTEM_MODE)
                    },
                )
            },
        )
    }
}




