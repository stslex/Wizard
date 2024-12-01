package com.stslex.wizard.feature.match.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.kit.components.AppSnackbarHost
import com.stslex.wizard.feature.match.ui.components.MatchScreenContent
import com.stslex.wizard.feature.match.ui.components.MatchScreenEmpty
import com.stslex.wizard.feature.match.ui.components.MatchScreenError
import com.stslex.wizard.feature.match.ui.components.MatchScreenShimmer
import com.stslex.wizard.feature.match.ui.store.MatchScreenState
import com.stslex.wizard.feature.match.ui.store.MatchStore.Action
import com.stslex.wizard.feature.match.ui.store.MatchStore.State

@Composable
internal fun MatchScreen(
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
