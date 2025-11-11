package com.codeplace.postsandroidapp.feature_explore.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.core.presentation.theme.CornerRadiusSize
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize
import com.example.compose.PostsAndroidAppTheme

@Composable
fun CommentCard(
    name: String,
    email: String,
    body: String,
    modifier: Modifier = Modifier,
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        shape = RoundedCornerShape(CornerRadiusSize.small)
    ) {
        Column(
            modifier = modifier
                .padding(all = SpacingSize.large),
            verticalArrangement = Arrangement.spacedBy(SpacingSize.extraSmall)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(SpacingSize.nano)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface

                )
                Text(
                    text = email,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CommentsCardPreview() {
    PostsAndroidAppTheme {
        CommentCard(
            name = "Lorem ipsum dolor sit amet consectetur",
            email = "ikita@garfield.biz",
            body = "Lorem ipsum dolor sit amet consectetur. Lorem velit blandit facilisis sollicitudin et molestie mattis tortor. Fusce vitae ut feugiat adipiscing aenean pellentesque ac sagittis nulla. Justo sed dictumst adipiscing egestas phasellus. Erat congue dictum id aenean massa viverra aliquam."
        )
    }

}
