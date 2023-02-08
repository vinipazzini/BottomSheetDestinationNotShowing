package com.example.missingcomposable.component

import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@ExperimentalMaterialNavigationApi
@Composable
fun ModalBottomSheetLayout(
    bottomSheetNavigator: BottomSheetNavigator,
    modifier: Modifier = Modifier,
    sheetShape: Shape = BottomSheetDefaults.Shape,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
    content: @Composable () -> Unit,
) {
    // LMOB-285: Migrate to Material 3 version once available
    com.google.accompanist.navigation.material.ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        modifier = modifier,
        sheetShape = sheetShape,
        sheetElevation = sheetElevation,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        scrimColor = scrimColor,
        content = content,
    )
}
