package com.codeplace.postsandroidapp.feature_settings.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.components.DefaultTopAppBar
import com.example.compose.PostsAndroidAppTheme

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PostsAndroidAppTheme {
        SettingsScreen(onThemeClick = {})
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenRoot(
    onThemeClick: () -> Unit = {},
) {

    SettingsScreen(
        onThemeClick = onThemeClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onThemeClick: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 32.dp)
    ) {
        DefaultTopAppBar(
            content = {
                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(R.string.top_bar_title_settings)

                )
            }
        )


        ListItem(
            modifier = Modifier
                .clickable { onThemeClick() },
            headlineContent = {
                Text(text = stringResource(R.string.theme_settings_item))

            },
            leadingContent = {
                Icon(
                    Icons.Outlined.LightMode,
                    contentDescription = null,
                )
            },
        )
    }
}