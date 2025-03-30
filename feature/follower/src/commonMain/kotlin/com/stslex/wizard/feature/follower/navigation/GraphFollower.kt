package com.stslex.wizard.feature.follower.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.navComponentScreen
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.follower.ui.FollowerScreen
import com.stslex.wizard.feature.follower.ui.store.FollowerStore
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreImpl

fun NavGraphBuilder.graphFollower() {
    navComponentScreen<Screen.Follower, FollowerStore, FollowerStoreImpl> { screen, store ->
        val store = getStore<FollowerStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(key1 = Unit) {
            store.consume(
                Action.Init(
                    followerType = screen.type,
                    uuid = screen.uuid
                )
            )
        }

        FollowerScreen(
            state = state,
            onAction = store::consume
        )
    }
}