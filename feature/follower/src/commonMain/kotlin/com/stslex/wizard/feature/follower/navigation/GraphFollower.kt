package com.stslex.wizard.feature.follower.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.kit.mvi.getStore
import com.stslex.wizard.feature.follower.ui.FollowerScreen
import com.stslex.wizard.feature.follower.ui.store.FollowerStore
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.Action

fun NavGraphBuilder.graphFollower() {
    navScreen<Screen.Follower> { screen ->
        val store = getStore<FollowerStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(key1 = Unit) {
            store.sendAction(
                Action.Init(
                    followerType = screen.type,
                    uuid = screen.uuid
                )
            )
        }

        FollowerScreen(
            state = state,
            onAction = store::sendAction
        )
    }
}