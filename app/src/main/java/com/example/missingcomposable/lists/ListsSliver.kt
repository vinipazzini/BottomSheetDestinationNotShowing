package com.example.missingcomposable.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.missingcomposable.theme.Spacing

@Composable
fun ListsSliver(
    viewModel: ListsViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        ListHeader(" Lists ") {
            Column {
                Button(
                    onClick = { viewModel.navigate() },
                    modifier = Modifier.padding(horizontal = Spacing.x01),
                ) {
                    Text("Add New")
                }
            }
        }
    }
}
