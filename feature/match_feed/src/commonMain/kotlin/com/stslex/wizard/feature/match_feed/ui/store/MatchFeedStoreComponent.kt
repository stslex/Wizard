package com.stslex.wizard.feature.match_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.StoreComponent
import com.stslex.feature.match_feed.ui.components.SwipeDirection
import com.stslex.feature.match_feed.ui.model.FilmUi
import com.stslex.feature.match_feed.ui.model.MatchUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface MatchFeedStoreComponent : StoreComponent {

    @Stable
    data class State(
        val films: ImmutableList<FilmUi>,
        val screen: ScreenState,
        val match: MatchUi?,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : StoreComponent.State {
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

    sealed interface Event : StoreComponent.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation {

        data class Film(val uuid: String) : Navigation
    }

    sealed interface Action : StoreComponent.Action {

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
    }
}

