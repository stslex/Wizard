package com.stslex.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.core.network.api.server.ErrorRefresh
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.profile.ui.store.ProfileScreenState
import com.stslex.feature.profile.ui.store.ProfileStore
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.State

object ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<ProfileStore>()
        LaunchedEffect(Unit) {
            store.sendAction(Action.LoadProfile("uuid"))
        }
        val state by remember { store.state }.collectAsState()
        ProfileScreenContent(
            state = state,
            onAction = store::sendAction
        )
    }
}

@Composable
private fun ProfileScreenContent(state: State, onAction: (Action) -> Unit) {
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

        ProfileScreenState.Shimmer -> ProfileScreenShinner()
    }
}

@Composable
internal fun ProfileScreenShinner(modifier: Modifier = Modifier) {
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
            modifier = modifier,
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

@Composable
internal fun ProfileScreenContent(
    state: ProfileScreenState.Content,
    onAction: (Action) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
        ) {
            TextButton(
                modifier = modifier,
                onClick = {
                    onAction(Action.Logout)
                }
            ) {
                Text(text = "Logout")
            }
            Text(
                modifier = modifier,
                text = state.data.username
            )
        }

        if (state is ProfileScreenState.Content.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}