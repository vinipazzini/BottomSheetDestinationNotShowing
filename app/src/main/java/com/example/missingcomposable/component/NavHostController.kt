package com.example.missingcomposable.component

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavHostController: ProvidableCompositionLocal<NavHostController> =
    compositionLocalOf { error("No NavHostController provided") }

fun <T> NavHostController.setResult(key: String, value: T): Boolean =
    previousBackStackEntry?.run { savedStateHandle.set(key, value) } != null
