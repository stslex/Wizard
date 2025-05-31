package com.stslex.wizard.feature.match_feed.ui.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.match_feed.ui.components.SwipeDirection
import com.stslex.wizard.feature.match_feed.ui.model.FilmUi
import com.stslex.wizard.feature.match_feed.ui.model.MatchUi
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface MatchFeedStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val uuid: String,
        val films: ImmutableList<FilmUi>,
        val screen: ScreenState,
        val match: MatchUi?,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : Store.State {

        companion object {

            fun init(uuid: String) = State(
                uuid = uuid,
                screen = ScreenState.Loading,
                films = emptyList<FilmUi>().toImmutableList(),
                currentPage = 0,
                hasNextPage = true,
                match = null
            )
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        sealed interface Common : Action {

            @Stable
            data object Init : Common

            @Stable
            data object LoadFilms : Common
        }

        @Stable
        sealed interface User : Action {

            @Stable
            data class FilmClick(
                val uuid: String
            ) : User

            @Stable
            data class FilmSwiped(
                val direction: SwipeDirection,
                val uuid: String
            ) : User
        }

        sealed interface Navigation : Action, Store.Action.Navigation {

            data class Film(val uuid: String) : Navigation
        }

    }
}