package com.stslex.feature.settings.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.components.AppSnackbarHost
import com.stslex.core.ui.components.AppToolbar
import com.stslex.core.ui.mvi.getStore
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.settings.ui.components.SettingsContent
import com.stslex.feature.settings.ui.store.SettingsStore
import com.stslex.feature.settings.ui.store.SettingsStore.Action
import com.stslex.feature.settings.ui.store.SettingsStore.Event
import com.stslex.feature.settings.ui.store.SettingsStore.State

object SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val store = getStore<SettingsStore>()
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
        SettingsScreen(
            state = state,
            onAction = store::sendAction,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun SettingsScreen(
    state: State,
    onAction: (Action) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
        ) {
            AppToolbar(
                modifier = modifier,
                title = "Settings", // todo("resources")
                onBackClick = { onAction(Action.BackButtonClicked) }
            )
            Spacer(modifier = Modifier.height(AppDimension.Padding.big))
            SettingsContent(
                logOut = { onAction(Action.LogOut) }
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        AppSnackbarHost(
            snackbarHostState = snackbarHostState
        )
    }
}
