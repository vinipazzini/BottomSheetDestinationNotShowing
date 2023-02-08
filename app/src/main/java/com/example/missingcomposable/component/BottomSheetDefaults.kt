package com.example.missingcomposable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.missingcomposable.theme.Spacing

@Composable
fun BottomSheetGripper(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .padding(top = Spacing.x01)
            .size(24.dp, 4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
            .then(modifier),
    )
}

object BottomSheetDefaults {
    val Shape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(
            topStart = MaterialTheme.shapes.extraLarge.topStart,
            topEnd = MaterialTheme.shapes.extraLarge.topEnd,
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp),
        )

    val tonalElevation: Dp = 8.dp
}
