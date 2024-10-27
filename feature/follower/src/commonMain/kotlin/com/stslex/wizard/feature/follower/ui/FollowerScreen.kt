package com.stslex.wizard.feature.follower.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.base.paging.PagingColumn
import com.stslex.core.ui.mvi.getStore
import com.stslex.wizard.feature.follower.navigation.FollowerScreenArgs
import com.stslex.wizard.feature.follower.ui.store.FollowerScreenState
import com.stslex.wizard.feature.follower.ui.store.FollowerStore
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreComponent.State

data class FollowerScreen(
    val args: FollowerScreenArgs
) : Screen {

    @Composable
    override fun Content() {
        val store = getStore<FollowerStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(key1 = Unit) {
            store.sendAction(Action.Init(args = args))
        }

        FollowerScreen(
            state = state,
            onAction = store::sendAction
        )
    }
}


@Composable
internal fun FollowerScreen(
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