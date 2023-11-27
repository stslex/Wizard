package com.stslex.feature.film.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store

interface FilmStoreComponent : Store {

    @Stable
    data class State(
        val screenState: FilmScreenState
    ) : Store.State {

        companion object {
            val INITIAL = State(
                screenState = FilmScreenState.Loading
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(val id: String) : Action
    }

    sealed interface Event : Store.Event

    sealed interface Navigation : Store.Navigation{

        data object Back : Navigation
    }
}