package com.stslex.feature.follower.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.feature.follower.ui.store.FollowerStore
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Action
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.State

data class FollowerScreen(
    val args: FollowerScreenArgs
) : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<FollowerStore>()
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
    Text("test")
}