package com.stslex.wizard.feature.settings.ui.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.State

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

        @Stable
        sealed interface Click : Action {

            @Stable
            data object LogOut : Click

            @Stable
            data object BackButton : Click
        }

        @Stable
        sealed interface Navigation : Action, Store.Action.Navigation {

            @Stable
            data object Back : Navigation

            @Stable
            data object LogOut : Navigation
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}