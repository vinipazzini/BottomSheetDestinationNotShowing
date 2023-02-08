package com.example.missingcomposable.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.missingcomposable.theme.Spacing

@Composable
fun ListHeader(
    text: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(top = Spacing.x02),
            horizontalArrangement = Arrangement.spacedBy(Spacing.x01),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = Spacing.x02,
                        top = action?.let { Spacing.x01 } ?: 0.dp,
                        end = Spacing.x02,
                        bottom = action?.let { Spacing.x01 } ?: Spacing.half,
                    ),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                action?.invoke()
            }
        }
    }
}
