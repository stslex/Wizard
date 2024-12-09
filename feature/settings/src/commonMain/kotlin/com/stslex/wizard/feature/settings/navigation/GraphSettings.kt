package com.stslex.wizard.feature.settings.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.settings.ui.SettingsScreen
import com.stslex.wizard.feature.settings.ui.store.SettingsStoreImpl
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Event

fun NavGraphBuilder.graphSettings() {
    navScreen<Screen.Settings> {
        val store = getStore<SettingsStoreImpl>()
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
        SettingsScreen(
            state = state,
            onAction = store::sendAction,
            snackbarHostState = snackbarHostState
        )
    }
}