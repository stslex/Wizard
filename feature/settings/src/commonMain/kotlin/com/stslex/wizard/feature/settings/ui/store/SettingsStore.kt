package com.stslex.wizard.feature.settings.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.State

interface SettingsStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val isLoading: Boolean
    ) : Store.State {

        companion object {

            val INITIAL = State(isLoading = false)
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        data object LogOut : Action

        data object BackButtonClicked : Action

        sealed interface Navigation : Action, Store.Action.Navigation {

            data object Back : Navigation

            data object LogOut : Navigation
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}