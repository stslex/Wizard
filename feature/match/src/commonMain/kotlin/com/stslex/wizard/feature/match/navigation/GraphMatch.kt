package com.stslex.wizard.feature.match.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.match.ui.MatchScreen
import com.stslex.wizard.feature.match.ui.store.MatchStore
import com.stslex.wizard.feature.match.ui.store.MatchStore.Action
import com.stslex.wizard.feature.match.ui.store.MatchStore.Event

fun NavGraphBuilder.graphMatch() {
    navScreen<Screen.Match> { screen ->
        val store = getStore<MatchStore>()
        LaunchedEffect(Unit) {
            store.consume(
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

        MatchScreen(
            state = state,
            onAction = store::consume,
            snackbarHostState = snackbarHostState
        )
    }
}