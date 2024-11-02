package com.stslex.wizard.feature.match_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.match_feed.ui.components.SwipeDirection
import com.stslex.wizard.feature.match_feed.ui.model.FilmUi
import com.stslex.wizard.feature.match_feed.ui.model.MatchUi
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface MatchFeedStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val films: ImmutableList<FilmUi>,
        val screen: ScreenState,
        val match: MatchUi?,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : Store.State {

        companion object {
            val INITIAL = State(
                screen = ScreenState.Loading,
                films = emptyList<FilmUi>().toImmutableList(),
                currentPage = 0,
                hasNextPage = true,
                match = null
            )
        }
    }

    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Action : Store.Action {

        data object Init : Action

        data object LoadFilms : Action

        @Stable
        data class FilmClick(
            val uuid: String
        ) : Action

        @Stable
        data class FilmSwiped(
            val direction: SwipeDirection,
            val uuid: String
        ) : Action


        sealed interface Navigation : Action, Store.Action.Navigation {

            data class Film(val uuid: String) : Navigation
        }

    }
}

