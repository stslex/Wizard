package com.stslex.wizard.feature.auth.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.auth.di.AuthFeature
import com.stslex.wizard.feature.auth.mvi.AuthStore.Event
import com.stslex.wizard.feature.auth.mvi.handler.AuthComponent
import com.stslex.wizard.feature.auth.ui.model.screen.rememberAuthScreenState

@Composable
fun AuthInitScreen(
    component: AuthComponent
) {
    NavComponentScreen(AuthFeature, component) { processor ->
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

        val authScreenState = rememberAuthScreenState(
            snackbarHostState = snackbarHostState,
            screenState = processor.state.value,
            processAction = processor::consume
        )
        AuthScreenWidget(authScreenState)
    }
}
