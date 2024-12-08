package com.stslex.wizard.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.favourite.ui.model.FavouriteModel
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.State

interface FavouriteStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val uuid: String,
        val query: String,
        val paging: PagingUiState<FavouriteModel>,
        val screen: FavouriteScreenState,
        val isLoading: Boolean
    ) : Store.State {

        companion object {

            val INITIAL = State(
                uuid = "",
                query = "",
                paging = PagingUiState.default(PagingConfig.DEFAULT),
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

        sealed interface Navigation : Action, Store.Action.Navigation {

            data class OpenFilm(val uuid: String) : Navigation
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}