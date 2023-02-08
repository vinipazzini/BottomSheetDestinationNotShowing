package com.example.missingcomposable.addlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.missingcomposable.addlist.AddListBottomSheetContract.Effect
import com.example.missingcomposable.addlist.AddListBottomSheetContract.Event
import com.example.missingcomposable.addlist.AddListBottomSheetContract.State
import com.example.missingcomposable.component.OutlinedTextField
import com.example.missingcomposable.component.BottomSheetGripper
import com.example.missingcomposable.component.LocalNavHostController
import com.example.missingcomposable.component.setResult
import com.example.missingcomposable.theme.Spacing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun AddListBottomSheetScreen(viewModel: AddListBottomSheetViewModel = hiltViewModel()) {
    AddListBottomSheetScreen(
        state = viewModel.viewState.value,
        sendEvent = { viewModel.onUiEvent(it) },
        effectFlow = viewModel.effect,
    )
}

@Composable
private fun AddListBottomSheetScreen(
    state: State,
    effectFlow: Flow<Effect>,
    sendEvent: (event: Event) -> Unit,
) {
    val navHostController = LocalNavHostController.current
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                is Effect.SetResult -> {
                    navHostController.setResult(AddListBottomSheetDestination.route, effect.result)
                    if (effect.navigateBack) {
                        effect.linkNavigator.navigateBack()
                    }
                }
            }
        }.collect()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .imePadding()
            .padding(horizontal = Spacing.x02),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomSheetGripper()
        Text(
            text = "people_section_lists_add_new_dialog_hl",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.x02),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = "people_section_lists_add_new_dialogue_sl",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.half, bottom = Spacing.x02),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        OutlinedTextField(
            value = state.title,
            onValueChange = { value ->
                sendEvent(Event.OnTitleChanged(value))
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.loading,
            label = "people_section_lists_add_new_dialogue_inline_label",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (state.title.isNotEmpty()) {
                        sendEvent(Event.OnCreateButtonClicked)
                    }
                },
            ),
        )
        ActionSection(state, sendEvent)
    }
}

@Composable
private fun ActionSection(
    state: State,
    sendEvent: (event: Event) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = Spacing.x02)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.x02),
    ) {
        OutlinedButton(
            onClick = { sendEvent(Event.OnCancelButtonClicked) },
            modifier = Modifier.weight(1f),
            enabled = !state.loading,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
        ) { Text("action_cancel") }
        Button(
            onClick = { sendEvent(Event.OnCreateButtonClicked) },
            modifier = Modifier.weight(1f),
            enabled = !state.loading && state.title.isNotEmpty(),
        ) { Text("action_create") }
    }
}
