package com.stslex.wizard.feature.match.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.match.di.MatchFeature
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import com.stslex.wizard.feature.match.ui.mvi.handlers.MatchComponent

@Composable
fun MatchScreen(component: MatchComponent) {
    NavComponentScreen(MatchFeature, component) { processor ->
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
        MatchScreenWidget(
            state = processor.state.value,
            onAction = processor::consume,
            snackbarHostState = snackbarHostState
        )
    }
}
