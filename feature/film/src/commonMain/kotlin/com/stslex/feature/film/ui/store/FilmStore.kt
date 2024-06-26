package com.stslex.feature.film.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.film.ui.store.FilmStore.Action
import com.stslex.feature.film.ui.store.FilmStore.Event
import com.stslex.feature.film.ui.store.FilmStore.State

interface FilmStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val filmId: String,
        val screenState: FilmScreenState
    ) : Store.State {

        companion object {
            val INITIAL = State(
                filmId = "",
                screenState = FilmScreenState.Loading
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(val id: String) : Action

        data object BackButtonClick : Action

        data object LikeButtonClick : Action
    }

    sealed interface Event : Store.Event {

        data class ErrorSnackbar(val throwable: Throwable) : Event
    }

    sealed interface Navigation : Store.Navigation {

        data object Back : Navigation
    }
}