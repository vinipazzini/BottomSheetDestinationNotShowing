package com.example.missingcomposable.lists

import androidx.compose.runtime.Immutable
import com.example.missingcomposable.main.ViewEffect
import com.example.missingcomposable.main.ViewEvent
import com.example.missingcomposable.main.ViewState

@Immutable
object ListsContract {
    object State : ViewState

    sealed class Event : ViewEvent

    sealed class Effect : ViewEffect
}
