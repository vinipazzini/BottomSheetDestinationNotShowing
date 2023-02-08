package com.example.missingcomposable.addlist

import androidx.compose.runtime.Immutable
import com.example.missingcomposable.navigator.LinkNavigator
import com.example.missingcomposable.main.ViewEffect
import com.example.missingcomposable.main.ViewEvent
import com.example.missingcomposable.main.ViewState

@Immutable
object AddListBottomSheetContract {
    data class State(
        val loading: Boolean = false,
        val title: String = "",
    ) : ViewState

    sealed class Event : ViewEvent {
        data class OnTitleChanged(val title: String) : Event()
        object OnCancelButtonClicked : Event()
        object OnCreateButtonClicked : Event()
    }

    sealed class Effect : ViewEffect {
        data class SetResult(
            val result: String,
            val navigateBack: Boolean,
            val linkNavigator: LinkNavigator,
        ) : Effect()
    }
}
