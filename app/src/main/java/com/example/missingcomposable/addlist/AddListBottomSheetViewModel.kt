package com.example.missingcomposable.addlist

import com.example.missingcomposable.addlist.AddListBottomSheetContract.Effect
import com.example.missingcomposable.addlist.AddListBottomSheetContract.Event
import com.example.missingcomposable.addlist.AddListBottomSheetContract.State
import com.example.missingcomposable.main.BaseViewModel
import com.example.missingcomposable.navigator.LinkNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddListBottomSheetViewModel @Inject constructor(
    private val linkNavigator: LinkNavigator,
) : BaseViewModel<Event, State, Effect>() {
    override fun provideInitialState() = State()

    override fun onLoadingChanged(loading: Boolean) {
        updateState { copy(loading = loading) }
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnCancelButtonClicked -> linkNavigator.navigateBack()
            is Event.OnCreateButtonClicked -> handleOnCreateButtonClicked()
            is Event.OnTitleChanged -> updateState { copy(title = event.title) }
        }
    }

    private fun handleOnCreateButtonClicked() {
        sendEffect {
            Effect.SetResult(
                result = "",
                navigateBack = true,
                linkNavigator = linkNavigator,
            )
        }
    }
}
