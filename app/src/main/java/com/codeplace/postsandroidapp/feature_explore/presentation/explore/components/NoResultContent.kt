package com.codeplace.postsandroidapp.feature_explore.presentation.explore.components

import com.codeplace.postsandroidapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun NoResultFoundContentPreview() {
    MaterialTheme {
        NoResultFoundContent()
    }

}

@Composable
fun NoResultFoundContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Default.SearchOff,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_result_found),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold

        )
        Text(
            text = stringResource(R.string.no_matching_item),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant

        )
    }

}