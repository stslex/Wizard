package com.stslex.wizard.feature.profile.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.stslex.wizard.core.navigation.v2.Config.BottomBar.Profile.Type
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.profile.di.ProfileFeature
import com.stslex.wizard.feature.profile.navigation.ProfileComponent
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event

@Composable
fun ProfileScreen(
    component: ProfileComponent,
    type: Type,
    uuid: String
) {
    NavComponentScreen(ProfileFeature, component) { processor ->
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
                    type = type,
                    uuid = uuid
                )
            )
        }

        ProfileScreenWidget(
            state = processor.state,
            onAction = processor::consume,
            snackbarHostState = snackbarHostState
        )
    }
}
