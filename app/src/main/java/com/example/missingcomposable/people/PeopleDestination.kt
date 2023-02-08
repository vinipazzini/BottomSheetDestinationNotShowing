package com.example.missingcomposable.people

import android.net.Uri
import com.example.missingcomposable.navigator.NavigationDestination

object PeopleDestination : NavigationDestination() {
    const val NAME: String = "people"

    override val route: String = Uri.Builder()
        .appendPath(NAME)
        .build()
        .toString()
        .removePrefix("/")


    fun `get`(): String = Uri.Builder().apply {
        appendPath(NAME)
    }
        .build()
        .toString()
        .removePrefix("/")

}
