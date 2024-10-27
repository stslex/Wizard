package com.stslex.wizard.feature.favourite.ui.components.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.favourite.ui.store.FavouriteScreenState

@Composable
internal fun FavouriteScreenError(
    state: FavouriteScreenState.Error,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Error: ${state.error.message}")
    }
}