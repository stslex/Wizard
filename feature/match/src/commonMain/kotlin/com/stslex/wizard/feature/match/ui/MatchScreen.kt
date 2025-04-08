package com.stslex.wizard.feature.match.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.navigation.v2.Config.BottomBar.Match.Type
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.match.navigation.MatchComponent
import com.stslex.wizard.feature.match.ui.store.MatchStore
import com.stslex.wizard.feature.match.ui.store.MatchStore.Action
import com.stslex.wizard.feature.match.ui.store.MatchStore.Event
import com.stslex.wizard.feature.match.ui.store.MatchStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun MatchScreen(
    component: MatchComponent,
    uuid: String,
    type: Type
) {
    val store = getStore<MatchStore, MatchStoreImpl>(
        parameters = { parametersOf(component) }
    )
    LaunchedEffect(Unit) {
        store.consume(
            Action.Init(
                type = type,
                uuid = uuid
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

    MatchScreenWidget(
        state = state,
        onAction = store::consume,
        snackbarHostState = snackbarHostState
    )
}
