package com.stslex.wizard.feature.film.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStore.Event
import com.stslex.wizard.feature.film.ui.store.FilmStore.State

interface FilmStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val uuid: String,
        val screenState: FilmScreenState
    ) : Store.State {

        companion object {

            fun initial(
                uuid: String
            ) = State(
                uuid = uuid,
                screenState = FilmScreenState.Loading
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data object Init : Action

        data object BackButtonClick : Action

        data object LikeButtonClick : Action

        sealed interface Navigation : Action, Store.Action.Navigation {

            data object Back : Navigation
        }
    }

    sealed interface Event : Store.Event {

        data class ErrorSnackbar(val throwable: Throwable) : Event
    }
}