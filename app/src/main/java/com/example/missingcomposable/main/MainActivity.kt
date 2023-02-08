package com.example.missingcomposable.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.missingcomposable.addlist.AddListBottomSheetDestination
import com.example.missingcomposable.addlist.AddListBottomSheetScreen
import com.example.missingcomposable.component.*
import com.example.missingcomposable.lists.ListsSliver
import com.example.missingcomposable.main.MainContract.Event
import com.example.missingcomposable.navigator.LinkNavigator
import com.example.missingcomposable.navigator.NavigationDestination
import com.example.missingcomposable.navigator.NavigatorEvent
import com.example.missingcomposable.people.PeopleDestination
import com.example.missingcomposable.theme.LinkTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var linkNavigator: LinkNavigator

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LinkTheme {
                LinkMainScreen(
                    startDestination = linkNavigator.homeDestination,
                    linkNavigator = linkNavigator,
                )
            }
        }
        viewModel.onUiEvent(Event.OnIntentReceived(intent))
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        viewModel.onUiEvent(Event.OnIntentReceived(intent, true))
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialNavigationApi::class)
@Suppress("ReusedModifierInstance")
@Composable
fun LinkMainScreen(
    linkNavigator: LinkNavigator,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipHalfExpanded = true)
    val navHostController = rememberNavController(bottomSheetNavigator)
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(navHostController) {
        linkNavigator.destinations.onEach { event ->
            keyboardController?.hide()
            when (event) {
                is NavigatorEvent.Directions -> navHostController.navigate(
                    event.destination,
                    event.builder,
                )
                is NavigatorEvent.HandleDeepLink -> navHostController.handleDeepLink(event.intent)
                is NavigatorEvent.NavigateUp -> navHostController.navigateUp()
                is NavigatorEvent.NavigateBack -> navHostController.popBackStack()
            }
        }.launchIn(this)
    }

    CompositionLocalProvider(
        LocalNavHostController provides navHostController,
    ) {
        Box {
            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                modifier = modifier.imePadding(),
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = startDestination,
                    modifier = Modifier.padding(16.dp),
                    builder = {
                        addBottomSheetDestinations()
                        addComposableDestinations()
                    },
                )
            }
        }
    }
}

fun NavGraphBuilder.addComposableDestinations() {
    mapOf<NavigationDestination, @Composable () -> Unit>(
        PeopleDestination to { ListsSliver() },
    )
        .forEach { entry ->
            val destination = entry.key
            composable(
                destination.route,
                destination.arguments,
                destination.deepLinks
            ) { entry.value() }
        }
}

@ExperimentalMaterialNavigationApi
fun NavGraphBuilder.addBottomSheetDestinations() {
    mapOf<NavigationDestination, @Composable () -> Unit>(
        AddListBottomSheetDestination to { AddListBottomSheetScreen() }
    )
        .forEach { entry ->
            val destination = entry.key
            bottomSheet(destination.route, destination.arguments, destination.deepLinks) {
                Surface(
                    tonalElevation = BottomSheetDefaults.tonalElevation,
                ) {
                    entry.value()
                }
            }
        }
}

