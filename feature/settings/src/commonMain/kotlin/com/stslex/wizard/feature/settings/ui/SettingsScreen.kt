package com.stslex.wizard.feature.settings.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.settings.di.SettingsFeature
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.mvi.handlers.SettingsComponent

@Composable
fun SettingsScreen(component: SettingsComponent) {
    NavComponentScreen(SettingsFeature, component) { processor ->
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
        SettingsScreenWidget(
            state = processor.state.value,
            onAction = processor::consume,
            snackbarHostState = snackbarHostState
        )
    }
}
