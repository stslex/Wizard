package com.stslex.wizard.feature.profile.ui.mvi.handlers

import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.di.ProfileScope
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Event
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(ProfileScope::class)
@Scoped
class LogoutHandler(
    private val interactor: ProfileInteractor,
) : Handler<Action.Logout, ProfileHandlerStore> {

    override fun ProfileHandlerStore.invoke(action: Action.Logout) {
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
                consume(Action.Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ShowSnackbar(Snackbar.Error(error.message ?: "error logout")))
            }
        )
    }
}