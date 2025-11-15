package com.codeplace.postsandroidapp.core.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.PostsAndroidAppTheme


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FeedBackCard(
    errorMessage: String,
    feedBackCard: FeedBackCard = FeedBackCard.ERROR
) {

    val containerColor = when (feedBackCard) {
        FeedBackCard.ERROR -> MaterialTheme.colorScheme.errorContainer
    }
    val contentColor = when (feedBackCard) {
        FeedBackCard.ERROR -> MaterialTheme.colorScheme.error
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
        ),
        shape = ShapeDefaults.Medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.error
        )
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ErrorMessageScreenPreview() {
    PostsAndroidAppTheme {
        FeedBackCard("Error teste")

    }
}


enum class FeedBackCard() {
    ERROR,

}