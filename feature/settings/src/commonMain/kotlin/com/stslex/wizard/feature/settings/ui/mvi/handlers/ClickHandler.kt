package com.stslex.wizard.feature.settings.ui.mvi.handlers

import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.settings.di.SettingsScope
import com.stslex.wizard.feature.settings.domain.SettingsInteractor
import com.stslex.wizard.feature.settings.ui.mvi.SettingsHandlerStore
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(SettingsScope::class)
@Scoped
internal class ClickHandler(
    private val interactor: SettingsInteractor
) : Handler<Action.Click, SettingsHandlerStore> {

    override fun SettingsHandlerStore.invoke(action: Action.Click) {
        when (action) {
            Action.Click.BackButton -> actionBackClick()
            Action.Click.LogOut -> actionLogout()
        }
    }

    private fun SettingsHandlerStore.actionBackClick() {
        consume(Action.Navigation.Back)
    }

    private fun SettingsHandlerStore.actionLogout() {
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