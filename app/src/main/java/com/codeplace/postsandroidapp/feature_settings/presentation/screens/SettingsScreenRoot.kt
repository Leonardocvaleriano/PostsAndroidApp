package com.codeplace.postsandroidapp.feature_settings.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.TopAppBarTrailingIcon
import com.example.compose.PostsAndroidAppTheme

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){
    PostsAndroidAppTheme{
        SettingsScreen(onThemeClick = {})
    }

}

@Composable
fun SettingsScreenRoot(
    onThemeClick: () -> Unit = {},
) {


    Scaffold(
        topBar = {
            TopAppBarTrailingIcon(
                title = stringResource(R.string.top_bar_title_settings)
            )
        }

    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            SettingsScreen(
                onThemeClick = onThemeClick
            )
        }
    }


}

@Composable
fun SettingsScreen(
    onThemeClick: () -> Unit,
) {
    Column {
        ListItem(
            modifier = Modifier.clickable {
                onThemeClick()
            },
            headlineContent = {
                Text(text = stringResource(R.string.theme_settings_item))

            },
            leadingContent = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null,
                )
            },
        )
    }
}