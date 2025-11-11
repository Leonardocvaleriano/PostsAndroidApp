package com.codeplace.postsandroidapp.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.example.compose.PostsAndroidAppTheme


@Composable
fun ErrorMessageText(
    errorMessage: String,
) {

    Column(modifier = Modifier
        .padding(
            horizontal = SpacingSize.large,
            vertical = SpacingSize.large
        ), verticalArrangement = Arrangement.spacedBy(SpacingSize.small)){
        Text(
            text = stringResource(R.string.title_oops_something_went_wrong),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )

    }

}

@Preview(showBackground = true)
@Composable
fun ErrorMessageScreenPreview() {
    PostsAndroidAppTheme {
        ErrorMessageText("Error teste")

    }
}