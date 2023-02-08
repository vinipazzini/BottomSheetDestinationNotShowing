package com.example.missingcomposable.lists

import com.example.missingcomposable.addlist.AddListBottomSheetDestination
import com.example.missingcomposable.main.BaseViewModel
import com.example.missingcomposable.navigator.LinkNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val linkNavigator: LinkNavigator,
) : BaseViewModel<ListsContract.Event, ListsContract.State, ListsContract.Effect>() {
    override fun provideInitialState() = ListsContract.State

    override fun handleEvent(event: ListsContract.Event) {
    }

    fun navigate() {
        linkNavigator.navigate(AddListBottomSheetDestination.get())
    }
}
