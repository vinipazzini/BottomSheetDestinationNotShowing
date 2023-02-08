package com.example.missingcomposable.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

/**
 * Create and remember a [BottomSheetNavigator]
 *
 * @return a [BottomSheetNavigator]
 */
@ExperimentalMaterialNavigationApi
@Composable
fun rememberBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = false,
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = animationSpec,
        skipHalfExpanded = skipHalfExpanded,
    )
    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
}

val BottomSheetScaffoldState.expandProgressFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue
        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 0f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 1f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> fraction
            else -> 1 - fraction
        }
    }
