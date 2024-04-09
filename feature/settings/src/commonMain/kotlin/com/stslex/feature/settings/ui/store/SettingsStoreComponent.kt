package com.stslex.feature.settings.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.Store.Event.Snackbar

interface SettingsStoreComponent : Store {

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
    }

    @Stable
    sealed interface Event : Store.Event {

        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : Store.Navigation {

        data object Back : Navigation

        data object LogOut : Navigation
    }
}