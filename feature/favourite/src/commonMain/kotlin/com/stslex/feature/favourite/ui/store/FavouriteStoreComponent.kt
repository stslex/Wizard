package com.stslex.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingUiState
import com.stslex.core.ui.mvi.StoreComponent
import com.stslex.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.feature.favourite.ui.model.FavouriteModel

interface FavouriteStoreComponent : StoreComponent {

    @Stable
    data class State(
        val uuid: String,
        val query: String,
        val paging: PagingUiState<FavouriteModel>,
        val screen: FavouriteScreenState,
        val isLoading: Boolean
    ) : StoreComponent.State {

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
    sealed interface Action : StoreComponent.Action {

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
    sealed interface Event : StoreComponent.Event {

        @Stable
        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation {

        data class OpenFilm(val uuid: String) : Navigation
    }
}