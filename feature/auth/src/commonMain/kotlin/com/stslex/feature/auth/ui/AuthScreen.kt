package com.stslex.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.swipeable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.stslex.core.ui.mvi.getStore
import com.stslex.core.ui.mvi.setupNavigator
import com.stslex.core.ui.theme.AppDimension
import com.stslex.core.ui.theme.toPx
import com.stslex.feature.auth.ui.components.AuthFieldsColumn
import com.stslex.feature.auth.ui.components.AuthTitle
import com.stslex.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.feature.auth.ui.store.AuthStore
import com.stslex.feature.auth.ui.store.AuthStore.AuthFieldsState
import com.stslex.feature.auth.ui.store.AuthStore.Event
import com.stslex.feature.auth.ui.store.AuthStore.ScreenLoadingState

object AuthScreen : Screen {

    @Composable
    override fun Content() {
        setupNavigator()
        val store = getStore<AuthStore>()

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
            processAction = store::sendAction
        )
        AuthScreen(authScreenState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AuthScreen(
    state: AuthScreenState
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        val screenWidth = maxWidth
        val screenWidthPx = screenWidth.toPx

        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(MaterialTheme.colorScheme.background)
                .swipeable(
                    state = state.swipeableState,
                    orientation = Orientation.Horizontal,
                    anchors = mapOf(
                        screenWidthPx to AuthFieldsState.AUTH,
                        0f to AuthFieldsState.REGISTER
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            AuthScreenContent(state)
            AppSnackbarHost(
                snackbarHostState = state.snackbarHostState,
                width = screenWidth
            )
        }

        if (state.screenLoadingState == ScreenLoadingState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f))
                    .systemBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AuthScreenContent(
    state: AuthScreenState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(AppDimension.Padding.big)
    ) {
        AuthTitle(
            modifier = Modifier.align(Alignment.TopCenter),
            swipeableState = state.swipeableState,
        )
        AuthFieldsColumn(
            modifier = Modifier.align(Alignment.Center),
            state = state
        )
    }
}