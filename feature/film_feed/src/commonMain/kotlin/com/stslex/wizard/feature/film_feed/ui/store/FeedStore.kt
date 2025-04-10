package com.stslex.wizard.feature.film_feed.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.Target
import com.stslex.wizard.core.ui.kit.pager.PagingUIList
import com.stslex.wizard.core.ui.kit.pager.PagingUIList.Companion.pagingUiListOf
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State

interface FeedStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val films: PagingUIList<FilmModel>,
        val screen: ScreenState,
        val currentPage: Int,
        val hasNextPage: Boolean
    ) : Store.State {

        companion object {

            val INITIAL = State(
                films = pagingUiListOf(),
                screen = ScreenState.Content.Shimmer,
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

        sealed interface Click : Action {

            @Stable
            data class FilmClick(val uuid: String) : Click
        }

        @Stable
        sealed interface Navigation : Action, Store.Action.Navigation, Target {

            data class Film(val uuid: String) : Navigation
        }
    }
}