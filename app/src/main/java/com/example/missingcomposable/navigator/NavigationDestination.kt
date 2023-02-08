package com.example.missingcomposable.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

abstract class NavigationDestination {
    abstract val route: String

    open val deepLinks: List<NavDeepLink>
        get() = emptyList()

    open val arguments: List<NamedNavArgument>
        get() = emptyList()

    abstract class Result {
        abstract val id: Long
        var consumed: Boolean = false

        companion object {
            val uniqueId
                get() = System.currentTimeMillis()
        }
    }
}
