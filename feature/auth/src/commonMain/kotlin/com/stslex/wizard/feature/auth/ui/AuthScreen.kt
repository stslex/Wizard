package com.stslex.wizard.feature.auth.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.auth.navigation.AuthComponent
import com.stslex.wizard.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.wizard.feature.auth.ui.store.AuthStore
import com.stslex.wizard.feature.auth.ui.store.AuthStore.Event
import com.stslex.wizard.feature.auth.ui.store.AuthStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun AuthInitScreen(
    component: AuthComponent
) {
    val store = getStore<AuthStore, AuthStoreImpl>(
        parameters = { parametersOf(component) }
    )
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

    val authScreenState = rememberAuthScreenState(
        snackbarHostState = snackbarHostState,
        screenState = state,
        processAction = store::consume
    )
    AuthScreenWidget(authScreenState)
}
