package com.stslex.wizard.feature.follower.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.kit.base.paging.PagingColumn
import com.stslex.wizard.feature.follower.ui.store.FollowerScreenState
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.State

@Composable
internal fun FollowerScreenWidget(
    state: State,
    onAction: (Action) -> Unit
) {
    when (state.screen) {
        is FollowerScreenState.Content -> {
            PagingColumn(
                pagingState = state.paging,
                onLoadNext = { onAction(Action.Load) },
                isAppend = state.screen is FollowerScreenState.Content.Loading,
                item = { item ->
                    Text(text = item.username)
                }
            )
        }

        is FollowerScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${state.screen.error.message}")
            }
        }

        FollowerScreenState.Shimmer -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Shimmer")
            }
        }

        FollowerScreenState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Empty")
            }
        }
    }
}