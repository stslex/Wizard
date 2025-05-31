package com.stslex.wizard.feature.follower.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.navigation.Config.Follower.FollowerType
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.follower.mvi.FollowerComponent
import com.stslex.wizard.feature.follower.mvi.FollowerStore
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun FollowerScreen(
    component: FollowerComponent,
    type: FollowerType,
    uuid: String
) {
    val store = getStore<FollowerStore, FollowerStoreImpl>(
        parameters = { parametersOf(component) }
    )
    val state by remember { store.state }.collectAsState()

    LaunchedEffect(key1 = Unit) {
        store.consume(
            Action.Init(
                followerType = type,
                uuid = uuid
            )
        )
    }

    FollowerScreenWidget(
        state = state,
        onAction = store::consume
    )
}