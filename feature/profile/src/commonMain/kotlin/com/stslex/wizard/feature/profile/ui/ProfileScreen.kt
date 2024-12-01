package com.stslex.wizard.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.core.error.ErrorRefresh
import com.stslex.wizard.core.ui.kit.components.AppSnackbarHost
import com.stslex.wizard.core.ui.kit.theme.AppDimension
import com.stslex.wizard.feature.profile.ui.components.ProfileScreenContent
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

@Composable
internal fun ProfileScreen(
    state: State,
    onAction: (Action) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        when (val screen = state.screen) {
            is ProfileScreenState.Content -> ProfileScreenContent(
                state = screen,
                onAction = onAction
            )

            is ProfileScreenState.Error -> ProfileScreenError(
                error = screen.error,
                logOut = { onAction(Action.Logout) },
                repeatLastAction = { onAction(Action.RepeatLastAction) }
            )

            ProfileScreenState.Shimmer -> ProfileScreenShimmer()
        }
        AppSnackbarHost(snackbarHostState)
    }
}

@Composable
internal fun ProfileScreenShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // TODO: Add shimmer
        CircularProgressIndicator()
    }
}

@Composable
internal fun ProfileScreenError(
    error: Throwable?,
    logOut: () -> Unit,
    repeatLastAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (error) {
                is ErrorRefresh -> {
                    Text(text = "Auth error")
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = logOut) {
                        Text(text = "logout")
                    }
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = repeatLastAction) {
                        Text(text = "Retry")
                    }
                }

                else -> {
                    Text(text = "Error: ${error?.message}")
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Button(onClick = repeatLastAction) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}
