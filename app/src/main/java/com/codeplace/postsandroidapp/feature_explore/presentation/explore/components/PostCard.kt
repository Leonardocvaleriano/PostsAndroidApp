package com.codeplace.postsandroidapp.feature_explore.presentation.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeplace.postsandroidapp.core.presentation.components.IconAction
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.codeplace.postsandroidapp.feature_explore.presentation.explore.previews.mockPostEntityCard
import com.example.compose.PostsAndroidAppTheme


@Preview
@Composable
fun PosdCardPreview() {
    PostsAndroidAppTheme {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            PostCard(
                post = mockPostEntityCard,
                onCardClick = {},
                showFavoriteIcon = true,
                onFavoriteClick = {}


            )
            PostCard(
                post = mockPostEntityCard,
                onCardClick = {},
                onFavoriteClick = {}

                )
        }

    }

}

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post,
    onCardClick: (postId: Int) -> Unit,
    showFavoriteIcon: Boolean = true,
    onFavoriteClick:(post: Post) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        shape = ShapeDefaults.Medium,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
            ) {
                onCardClick(post.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp,
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                        if (showFavoriteIcon) {
                            IconAction(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                ,
                                showBackground = false,
                                iconElement = {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                },
                                onClick = {
                                    onFavoriteClick(post)
                                }

                            )
                        }
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 48.dp),
                        text = post.title.replaceFirstChar {
                            it.uppercase()
                        },
                        lineHeight = 24.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    text = post.body.replaceFirstChar {
                        it.uppercase()
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    softWrap = true,
                    textAlign = TextAlign.Start
                )
            }


        }

    }

}


