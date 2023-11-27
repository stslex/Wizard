package com.stslex.feature.feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.feed.ui.model.FilmModel
import com.stslex.feature.feed.ui.model.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface FeedScreenStoreComponent : Store {

    @Stable
    data class State(
        val films: ImmutableList<FilmModel>,
        val screen: ScreenState,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : Store.State {

        companion object {
            val INITIAL = State(
                films = emptyList<FilmModel>().toImmutableList(),
                screen = ScreenState.Loading,
                currentPage = 0,
                hasNextPage = true
            )
        }
    }

    interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    @Stable
    interface Action : Store.Action {

        data object LoadFilms : Action

        @Stable
        data class FilmClick(val filmId: String) : Action

        @Stable
        data class GenreClick(val genreId: String) : Action
    }

    @Stable
    interface Navigation : Store.Navigation {

        data class Film(val filmId: String) : Navigation

        data class SearchGenre(val query: String) : Navigation
    }
}