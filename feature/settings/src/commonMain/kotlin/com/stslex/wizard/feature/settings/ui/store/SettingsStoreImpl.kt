package com.stslex.wizard.feature.settings.ui.store

import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.feature.settings.domain.SettingsInteractor
import com.stslex.wizard.feature.settings.navigation.SettingsComponent
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.State

class SettingsStoreImpl(
    private val interactor: SettingsInteractor,
    private val component: SettingsComponent,
) : SettingsStore, BaseStore<State, Action, Event>(State.INITIAL) {

    override fun process(action: Action) {
        when (action) {
            Action.LogOut -> actionLogout()
            Action.BackButtonClicked -> actionBackClick()
            is Action.Navigation -> processNavigation(action)
        }
    }

    private fun processNavigation(action: Action.Navigation) {
        when (action) {
            Action.Navigation.Back -> component.back()
            Action.Navigation.LogOut -> component.openAuth()
        }
    }

    private fun actionBackClick() {
        consume(Action.Navigation.Back)
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
                consume(Action.Navigation.LogOut)
            },
            onError = { error ->
                val message = error.message ?: "Logout error"
                val snackbarType = Snackbar.Error(message)
                sendEvent(Event.ShowSnackbar(snackbarType))
            }
        )
    }
}