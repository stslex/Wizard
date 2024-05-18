package com.stslex.feature.profile.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.network.api.server.model.ErrorRefresh
import com.stslex.core.ui.components.AppSnackbarHost
import com.stslex.core.ui.mvi.getStore
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.profile.navigation.ProfileScreenArguments
import com.stslex.feature.profile.ui.components.ProfileScreenContent
import com.stslex.feature.profile.ui.store.ProfileScreenState
import com.stslex.feature.profile.ui.store.ProfileStore
import com.stslex.feature.profile.ui.store.ProfileStore.Action
import com.stslex.feature.profile.ui.store.ProfileStore.Event
import com.stslex.feature.profile.ui.store.ProfileStore.State

data class ProfileScreen(
    val args: ProfileScreenArguments
) : Screen {

    @Composable
    override fun Content() {
        val store = getStore<ProfileStore>()
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
        ProfileScreen(
            state = state,
            onAction = store::sendAction,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun ProfileScreen(
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
