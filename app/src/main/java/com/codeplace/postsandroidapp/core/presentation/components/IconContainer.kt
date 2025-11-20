package com.codeplace.postsandroidapp.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun IconContainerPreview() {
    IconContainer()

}

@Composable
fun IconContainer(
    modifier: Modifier = Modifier,
    iconElement: @Composable (() -> Unit) = {}
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .size(40.dp),
        contentAlignment = Alignment.Center

    ){
        iconElement()

    }

}