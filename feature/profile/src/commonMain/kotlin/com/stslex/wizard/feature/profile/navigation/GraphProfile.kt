package com.stslex.wizard.feature.profile.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.v2.StoreBuilder
import com.stslex.wizard.core.ui.mvi.v2.navComponentScreenV2
import com.stslex.wizard.feature.profile.ui.ProfileScreen
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreImpl
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreProcessor

private val builder = StoreBuilder.create<ProfileStoreImpl, State, Action, Event>()

fun NavGraphBuilder.graphProfile() {
    navComponentScreenV2(builder) { screen: Screen.Profile, processor: ProfileStoreProcessor ->
        val snackbarHostState = remember { SnackbarHostState() }

        processor.handle { event ->
            when (event) {
                is Event.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = event.snackbar.message,
                    actionLabel = event.snackbar.action,
                    duration = event.snackbar.duration,
                    withDismissAction = event.snackbar.withDismissAction,
                )
            }
        }

        LaunchedEffect(Unit) {
            processor.consume(
                Action.Init(
                    type = screen.type,
                    uuid = screen.uuid
                )
            )
        }

        ProfileScreen(
            state = processor.state,
            onAction = processor::consume,
            snackbarHostState = snackbarHostState
        )
    }
}