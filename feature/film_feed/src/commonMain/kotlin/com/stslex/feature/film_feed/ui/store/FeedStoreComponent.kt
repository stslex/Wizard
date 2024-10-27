package com.stslex.feature.film_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.StoreComponent
import com.stslex.feature.film_feed.ui.model.FilmModel
import com.stslex.feature.film_feed.ui.model.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface FeedStoreComponent : StoreComponent {

    @Stable
    data class State(
        val films: ImmutableList<FilmModel>,
        val screen: ScreenState,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : StoreComponent.State {

        companion object {
            val INITIAL = State(
                films = persistentListOf(),
                screen = ScreenState.Loading,
                currentPage = 0,
                hasNextPage = true
            )
        }
    }

    sealed interface Event : StoreComponent.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        data object LoadFilms : Action

        @Stable
        data class FilmClick(val filmId: String) : Action
    }

    @Stable
    sealed interface Navigation : StoreComponent.Navigation {

        data class Film(val filmId: String) : Navigation
    }
}