package com.stslex.wizard.feature.auth.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.navComponentScreen
import com.stslex.wizard.feature.auth.ui.AuthScreen
import com.stslex.wizard.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.wizard.feature.auth.ui.store.AuthStore
import com.stslex.wizard.feature.auth.ui.store.AuthStore.Event
import com.stslex.wizard.feature.auth.ui.store.AuthStoreImpl

fun NavGraphBuilder.graphAuth() {
    navComponentScreen<Screen.Auth, AuthStore, AuthStoreImpl> { _, store ->
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
        AuthScreen(authScreenState)
    }
}