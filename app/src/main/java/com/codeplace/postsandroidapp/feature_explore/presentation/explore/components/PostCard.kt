package com.codeplace.postsandroidapp.feature_explore.presentation.explore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeplace.postsandroidapp.R
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import com.example.compose.PostsAndroidAppTheme

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post,
    onCardClick: (postId: Int) -> Unit,
    containCommentCount: Int? = 0,
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
            }

        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    all = SpacingSize.large
                ), verticalArrangement = Arrangement.spacedBy(SpacingSize.medium)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(SpacingSize.mini)
            ) {
                Text(
                    text = post.title.replaceFirstChar {
                        it.uppercase()
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = post.body.replaceFirstChar {
                        it.uppercase()
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (containCommentCount!! > 0) {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                )
                {
                    Row(modifier = modifier.padding(end = SpacingSize.small)) {
                        Text(
                            text = containCommentCount.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.padding(end = SpacingSize.nano))
                        Text(
                            text = stringResource(id = R.string.card_body_comments),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }

                }
            }
        }


    }
}


@Preview
@Composable
fun PosdCardPreview() {
    PostsAndroidAppTheme {

    }

}