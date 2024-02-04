package com.stslex.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.favourite.ui.model.FavouriteModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface FavouriteStoreComponent : Store {

    @Stable
    data class State(
        val uuid: String,
        val page: Int,
        val query: String,
        val data: ImmutableList<FavouriteModel>,
        val screen: FavouriteScreenState,
        val isLoading: Boolean
    ) : Store.State {

        companion object {

            const val DEFAULT_PAGE = -1

            val INITIAL = State(
                uuid = "",
                page = DEFAULT_PAGE,
                query = "",
                data = emptyList<FavouriteModel>().toImmutableList(),
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
        data class LikeClick(val uuid: String) : Action

        @Stable
        data class ItemClick(val uuid: String) : Action

        @Stable
        data class InputSearch(val query: String) : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Navigation : Store.Navigation {

        data class OpenFilm(val uuid: String) : Navigation
    }
}