package com.stslex.wizard.feature.film_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface FeedStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val films: ImmutableList<FilmModel>,
        val screen: ScreenState,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : Store.State {

        companion object {
            val INITIAL = State(
                films = persistentListOf(),
                screen = ScreenState.Loading,
                currentPage = 0,
                hasNextPage = true
            )
        }
    }

    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    @Stable
    sealed interface Action : Store.Action {

        data object LoadFilms : Action

        @Stable
        data class FilmClick(val filmId: String) : Action

        @Stable
        sealed interface Navigation : Action, Store.Action.Navigation {

            data class Film(val filmId: String) : Navigation
        }
    }
}