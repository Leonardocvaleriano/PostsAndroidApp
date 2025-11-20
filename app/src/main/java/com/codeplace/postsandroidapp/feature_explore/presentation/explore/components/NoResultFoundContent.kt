package com.codeplace.postsandroidapp.feature_explore.presentation.explore.components

import com.codeplace.postsandroidapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.PostsAndroidAppTheme

@Preview
@Composable
private fun NoResultFoundContentPreview() {
    PostsAndroidAppTheme {
        NoResultFoundContent()
    }

}

@Composable
fun NoResultFoundContent(
    modifier: Modifier = Modifier,
    onGoBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(194.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(112.dp),
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = stringResource(R.string.no_result_found),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold

        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(R.string.no_matching_item),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant

        )
        Spacer(modifier = Modifier.size(16.dp))

        Button(

            onClick = {
                onGoBackClick()

            }) {
            Text("Go back")
        }
    }

}