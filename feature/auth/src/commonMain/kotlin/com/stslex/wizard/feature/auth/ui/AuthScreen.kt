package com.stslex.wizard.feature.auth.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.components.AppSnackbarHost
import com.stslex.wizard.core.ui.theme.AppDimension
import com.stslex.wizard.core.ui.theme.toPx
import com.stslex.wizard.feature.auth.ui.components.AuthFieldsColumn
import com.stslex.wizard.feature.auth.ui.components.AuthTitle
import com.stslex.wizard.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.wizard.feature.auth.ui.store.AuthStoreComponent.AuthFieldsState
import com.stslex.wizard.feature.auth.ui.store.AuthStoreComponent.ScreenLoadingState

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun AuthScreen(
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