package com.stslex.wizard.feature.match.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.wizard.core.ui.components.AppSnackbarHost
import com.stslex.wizard.core.ui.mvi.getStore
import com.stslex.wizard.core.ui.navigation.args.MatchScreenArgs
import com.stslex.wizard.feature.match.ui.components.MatchScreenContent
import com.stslex.wizard.feature.match.ui.components.MatchScreenEmpty
import com.stslex.wizard.feature.match.ui.components.MatchScreenError
import com.stslex.wizard.feature.match.ui.components.MatchScreenShimmer
import com.stslex.wizard.feature.match.ui.store.MatchScreenState
import com.stslex.wizard.feature.match.ui.store.MatchStore
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.Action
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.Event
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.State

data class MatchScreen(
    private val args: MatchScreenArgs
) : Screen {

    @Composable
    override fun Content() {
        val store = getStore<MatchStore>()
        LaunchedEffect(Unit) {
            store.sendAction(Action.Init(args = args))
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
            onAction = store::sendAction,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun MatchScreen(
    state: State,
    onAction: (Action) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        when (val screen = state.screen) {
            is MatchScreenState.Content -> MatchScreenContent(
                state = state.paging,
                screen = screen,
                onAction = onAction
            )

            is MatchScreenState.Error -> MatchScreenError(
                error = screen.error,
                logOut = { onAction(Action.Logout) },
                repeatLastAction = { onAction(Action.RepeatLastAction) }
            )

            MatchScreenState.Empty -> MatchScreenEmpty()
            MatchScreenState.Shimmer -> MatchScreenShimmer()
        }
        AppSnackbarHost(snackbarHostState)
    }
}
