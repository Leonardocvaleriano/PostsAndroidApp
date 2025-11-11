package com.codeplace.postsandroidapp.feature_settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.codeplace.postsandroidapp.core.presentation.theme.SpacingSize

@Composable
fun RadioGroupItem(
    modifier: Modifier = Modifier,
    item: RadioButtonItem,
    selected: Boolean,
    supportText: String? = null,
    onClick: ((Int) -> Unit)?,
) {
    Row(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = { onClick?.invoke(item.id) },
                role = Role.RadioButton
            )
            .padding(SpacingSize.large)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (supportText != null) {
            Column(verticalArrangement = Arrangement.spacedBy(SpacingSize.nano)) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = supportText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Normal
                )
            }

        } else {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                )
        }
        Spacer(modifier = Modifier.width(SpacingSize.small))
        RadioButton(
            colors = RadioButtonColors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSelectedColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
                disabledUnselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            ),
            selected = selected,
            onClick = null,
        )

    }
}

@Preview
@Composable

fun RadioGroupItemPreview() {
    RadioGroupItem(
        item = RadioButtonItem(1, "Item 1"),
        selected = true,
        onClick = {}
    )
}