package com.stslex.feature.profile.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store

interface ProfileStoreComponent : Store {

    @Stable
    data class State(
        val screen: ProfileScreenState
    ) : Store.State {

        companion object {

            val INITIAL = State(screen = ProfileScreenState.Loading)
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class LoadProfile(
            val uuid: String
        ) : Action
    }

    @Stable
    sealed interface Event : Store.Event

    sealed interface Navigation : Store.Navigation
}