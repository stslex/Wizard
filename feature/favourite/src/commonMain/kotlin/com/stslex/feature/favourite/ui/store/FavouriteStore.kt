package com.stslex.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingState
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.feature.favourite.ui.model.FavouriteModel
import com.stslex.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.feature.favourite.ui.store.FavouriteStore.Event
import com.stslex.feature.favourite.ui.store.FavouriteStore.State

interface FavouriteStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val uuid: String,
        val query: String,
        val pagingState: PagingState<FavouriteModel>,
        val screen: FavouriteScreenState,
        val isLoading: Boolean
    ) : Store.State {

        companion object {

            val INITIAL = State(
                uuid = "",
                query = "",
                pagingState = PagingState.default(),
                screen = FavouriteScreenState.Shimmer,
                isLoading = true
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val uuid: String
        ) : Action

        @Stable
        data object LoadMore : Action

        @Stable
        data object Refresh : Action

        @Stable
        data object Retry : Action

        @Stable
        data class LikeClick(val uuid: String) : Action

        @Stable
        data class ItemClick(val uuid: String) : Action

        @Stable
        data class InputSearch(val query: String) : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : Store.Navigation {

        data class OpenFilm(val uuid: String) : Navigation
    }
}