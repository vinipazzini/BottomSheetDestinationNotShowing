package com.example.missingcomposable.main

import android.content.Intent
import androidx.compose.runtime.Immutable

@Immutable
object MainContract {
    data class State(
        val startDestination: String,
        val dynamicColors: Boolean = false,
        val mainNavigationBarEntries: Map<String, String> = emptyMap(),
    ) : ViewState

    sealed class Event : ViewEvent {
        data class OnIntentReceived(val intent: Intent, val isNewIntent: Boolean = false) : Event()
    }

    sealed class Effect : ViewEffect {
    }
}
