package com.codeplace.postsandroidapp.core.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun IconActionPreview() {
    Column {
        IconAction(
            onClick = {},
            iconElement = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

        )
        IconAction(
            showBackground = true,
            onClick = {},
            iconElement = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

        )
    }


}

@Composable
fun IconAction(
    modifier: Modifier = Modifier,
    iconElement: @Composable (() -> Unit) = {},
    onClick:() -> Unit,
    showBackground: Boolean = false
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()


    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) MaterialTheme.colorScheme.surfaceContainerHigh else if (showBackground){
            MaterialTheme.colorScheme.surfaceContainer
        } else { Color.Unspecified},
        label = "backgroundIconActionColor"
    )

    Box(
        modifier = modifier
            .clickable{
                onClick()
            }
            .clip(CircleShape)
            .background(backgroundColor)
            .size(48.dp),
        contentAlignment = Alignment.Center

    ){
        iconElement()

    }

}