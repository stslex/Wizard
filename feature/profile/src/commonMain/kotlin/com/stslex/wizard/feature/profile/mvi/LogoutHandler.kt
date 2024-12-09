package com.stslex.wizard.feature.profile.mvi

import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

class LogoutHandler(
    private val interactor: ProfileInteractor,
) : Handler<State, Action.Logout, Event, Action>(Action.Logout::class) {

    override fun HandlerStore<State, Action, Event>.invoke(action: Action.Logout) {
        val currentScreen = state.value.screen

        if (
            currentScreen is ProfileScreenState.Content.Loading ||
            currentScreen is ProfileScreenState.Shimmer
        ) {
            return
        }

        updateState { currentState ->
            currentState.copy(
                screen = ProfileScreenState.Shimmer
            )
        }

        launch(
            action = {
                interactor.logOut()
            },
            onSuccess = {
                sendAction(Action.Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ShowSnackbar(Snackbar.Error(error.message ?: "error logout")))
            }
        )
    }
}