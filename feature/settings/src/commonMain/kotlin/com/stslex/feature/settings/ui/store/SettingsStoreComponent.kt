package com.stslex.feature.settings.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.StoreComponent
import com.stslex.core.ui.mvi.StoreComponent.Event.Snackbar

interface SettingsStoreComponent : StoreComponent {

    @Stable
    data class State(
        val isLoading: Boolean
    ) : StoreComponent.State {

        companion object {

            val INITIAL = State(isLoading = false)
        }
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        data object LogOut : Action

        data object BackButtonClicked : Action
    }

    @Stable
    sealed interface Event : StoreComponent.Event {

        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation {

        data object Back : Navigation

        data object LogOut : Navigation
    }
}