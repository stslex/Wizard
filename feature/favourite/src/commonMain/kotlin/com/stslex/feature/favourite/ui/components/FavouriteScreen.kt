package com.stslex.feature.favourite.ui.components

import androidx.compose.runtime.Composable
import com.stslex.feature.favourite.ui.components.content.FavouriteScreenContent
import com.stslex.feature.favourite.ui.components.empty.FavouriteScreenEmpty
import com.stslex.feature.favourite.ui.components.error.FavouriteScreenError
import com.stslex.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer
import com.stslex.feature.favourite.ui.store.FavouriteScreenState
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.Action

@Composable
internal fun FavouriteScreen(
    state: FavouriteStoreComponent.State,
    onAction: (Action) -> Unit
) {
    when (state.screen) {
        is FavouriteScreenState.Content -> FavouriteScreenContent(
            state = state.screen,
            items = state.data,
            query = state.query,
            onItemClick = { uuid ->
                onAction(Action.ItemClick(uuid))
            },
            onLikeClick = { uuid ->
                onAction(Action.LikeClick(uuid))
            },
            onSearch = { query ->
                onAction(Action.InputSearch(query))
            }
        )

        is FavouriteScreenState.Error -> FavouriteScreenError(state = state.screen)
        FavouriteScreenState.Shimmer -> FavouriteScreenShimmer()
        FavouriteScreenState.Empty -> FavouriteScreenEmpty()
    }
}
