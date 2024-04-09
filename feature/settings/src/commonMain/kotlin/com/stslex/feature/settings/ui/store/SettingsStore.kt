package com.stslex.feature.settings.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.feature.settings.domain.SettingsInteractor
import com.stslex.feature.settings.navigation.SettingsRouter
import com.stslex.feature.settings.ui.store.SettingsStoreComponent.Action
import com.stslex.feature.settings.ui.store.SettingsStoreComponent.Event
import com.stslex.feature.settings.ui.store.SettingsStoreComponent.Navigation
import com.stslex.feature.settings.ui.store.SettingsStoreComponent.State

class SettingsStore(
    private val interactor: SettingsInteractor,
    router: SettingsRouter,
    appDispatcher: AppDispatcher
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {

    override fun process(action: Action) {
        when (action) {
            Action.LogOut -> actionLogout()
            Action.BackButtonClicked -> actionBackClick()
        }
    }

    private fun actionBackClick() {
        navigate(Navigation.Back)
    }

    private fun actionLogout() {
        if (state.value.isLoading) return
        updateState { currentState ->
            currentState.copy(
                isLoading = true
            )
        }

        launch(
            action = {
                interactor.logOut()
            },
            onSuccess = {
                updateState { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
                navigate(Navigation.LogOut)
            },
            onError = { error ->
                val message = error.message ?: "Logout error"
                val snackbarType = Snackbar.Error(message)
                sendEvent(Event.ShowSnackbar(snackbarType))
            }
        )
    }
}