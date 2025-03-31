package com.stslex.wizard.feature.settings.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.navComponentScreen
import com.stslex.wizard.feature.settings.ui.SettingsScreen
import com.stslex.wizard.feature.settings.ui.store.SettingsStore
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.store.SettingsStoreImpl

fun NavGraphBuilder.graphSettings() {
    navComponentScreen<Screen.Settings, SettingsStore, SettingsStoreImpl> { screen, store ->
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
            onAction = store::consume,
            snackbarHostState = snackbarHostState
        )
    }
}