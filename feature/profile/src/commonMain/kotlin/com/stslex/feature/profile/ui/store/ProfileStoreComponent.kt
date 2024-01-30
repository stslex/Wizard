package com.stslex.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store

interface ProfileStoreComponent : Store {

    @Stable
    data class State(
        val screen: ProfileScreenState
    ) : Store.State {

        companion object {

            val INITIAL = State(screen = ProfileScreenState.Shimmer)
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class LoadProfile(
            val uuid: String
        ) : Action

        data object Logout : Action

        data object RepeatLastAction : Action, Store.Action.RepeatLastAction
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Navigation : Store.Navigation {

        data object LogIn : Navigation
    }
}