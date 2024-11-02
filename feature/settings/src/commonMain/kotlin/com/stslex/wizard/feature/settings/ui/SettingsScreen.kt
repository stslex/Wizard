package com.stslex.wizard.feature.settings.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.components.AppSnackbarHost
import com.stslex.wizard.core.ui.components.AppToolbar
import com.stslex.wizard.core.ui.theme.AppDimension
import com.stslex.wizard.feature.settings.ui.components.SettingsContent
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.State

@Composable
internal fun SettingsScreen(
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
