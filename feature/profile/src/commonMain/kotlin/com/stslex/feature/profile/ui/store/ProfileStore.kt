package com.stslex.feature.profile.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.feature.profile.navigation.ProfileRouter
import com.stslex.feature.profile.ui.model.toUi
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Event
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Navigation
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.State

class ProfileStore(
    private val interactor: ProfileInteractor,
    router: ProfileRouter,
    appDispatcher: AppDispatcher,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {
    override fun process(action: Action) {
        when (action) {
            is Action.LoadProfile -> actionLoadProfile(action)
            is Action.Logout -> actionLogout()
            is Action.RepeatLastAction -> actionRepeatLastAction()
        }
    }

    private fun actionRepeatLastAction() {
        val lastAction = lastAction ?: return
        process(lastAction)
    }

    private fun actionLogout() {
        val currentScreen = state.value.screen

        if (
            currentScreen is ProfileScreenState.Content.Loading ||
            currentScreen is ProfileScreenState.Shimmer
        ) {
            return
        }

        updateState {
            it.copy(screen = ProfileScreenState.Shimmer)
        }
        launch(
            action = {
                interactor.logOut()
            },
            onSuccess = {
                navigate(Navigation.LogIn)
            },
            onError = { error ->
                sendEvent(Event.ErrorSnackBar(error.message ?: "error logout"))
            }
        )
    }

    private fun actionLoadProfile(action: Action.LoadProfile) {
        interactor.getProfile(action.uuid)
            .launchFlow(
                onError = {
                    updateState { currentState ->
                        currentState.copy(
                            screen = ProfileScreenState.Error(it)
                        )
                    }
                }
            ) { profile ->
                updateState { currentState ->
                    currentState.copy(
                        screen = ProfileScreenState.Content.NotLoading(profile.toUi())
                    )
                }
            }
    }
}
