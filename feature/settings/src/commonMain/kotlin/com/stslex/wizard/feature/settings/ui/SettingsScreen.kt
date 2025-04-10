package com.stslex.wizard.feature.settings.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.settings.navigation.SettingsComponent
import com.stslex.wizard.feature.settings.ui.store.SettingsStore
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.store.SettingsStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun SettingsScreen(
    component: SettingsComponent
) {
    val store = getStore<SettingsStore, SettingsStoreImpl> { parametersOf(component) }
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
    SettingsScreenWidget(
        state = state,
        onAction = store::consume,
        snackbarHostState = snackbarHostState
    )
}
