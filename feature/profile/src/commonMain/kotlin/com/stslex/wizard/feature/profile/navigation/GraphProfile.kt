package com.stslex.wizard.feature.profile.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.getStore
import com.stslex.wizard.feature.profile.ui.ProfileScreen
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreImpl
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event

fun NavGraphBuilder.graphProfile() {
    navScreen<Screen.Profile> { screen ->
        val store = getStore<ProfileStoreImpl>()
        LaunchedEffect(Unit) {
            store.sendAction(
                Action.Init(
                    type = screen.type,
                    uuid = screen.uuid
                )
            )
        }
        val state by remember { store.state }.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }
        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    is Event.ShowSnackbar -> snackbarHostState.showSnackbar(
                        message = event.snackbar.message,
                        actionLabel = event.snackbar.action,
                        duration = event.snackbar.duration,
                        withDismissAction = event.snackbar.withDismissAction,
                    )
                }
            }
        }
        ProfileScreen(
            state = state,
            onAction = store::sendAction,
            snackbarHostState = snackbarHostState
        )
    }
}