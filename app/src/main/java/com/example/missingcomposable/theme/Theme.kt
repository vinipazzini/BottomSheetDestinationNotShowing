package com.example.missingcomposable.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun LinkTheme(
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = lightColorScheme(),
        shapes = Shapes(),
        typography = Typography(),
        content = content,
    )
}
