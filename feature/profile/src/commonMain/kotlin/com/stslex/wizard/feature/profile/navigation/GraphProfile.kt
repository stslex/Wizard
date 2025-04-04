package com.stslex.wizard.feature.profile.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.v2.navComponentScreen
import com.stslex.wizard.feature.profile.di.ProfileFeature
import com.stslex.wizard.feature.profile.di.ProfileStoreProcessor
import com.stslex.wizard.feature.profile.ui.ProfileScreen
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event

fun NavGraphBuilder.graphProfile() {
    navComponentScreen<Screen.Profile, ProfileStoreProcessor>(ProfileFeature) { screen, processor ->
        val snackbarHostState = remember { SnackbarHostState() }

        processor.handle { event ->
            when (event) {
                is Event.ShowSnackbar -> snackbarHostState.showSnackbar(
                    message = event.snackbar.message,
                    actionLabel = event.snackbar.action,
                    duration = event.snackbar.duration,
                    withDismissAction = event.snackbar.withDismissAction,
                )
            }
        }

        LaunchedEffect(Unit) {
            processor.consume(
                Action.Init(
                    type = screen.type,
                    uuid = screen.uuid
                )
            )
        }

        ProfileScreen(
            state = processor.state,
            onAction = processor::consume,
            snackbarHostState = snackbarHostState
        )
    }
}