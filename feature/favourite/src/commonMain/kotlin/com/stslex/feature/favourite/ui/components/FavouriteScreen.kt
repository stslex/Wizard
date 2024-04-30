package com.stslex.feature.favourite.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.feature.favourite.ui.components.content.FavouriteScreenContent
import com.stslex.feature.favourite.ui.components.error.FavouriteScreenError
import com.stslex.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer
import com.stslex.feature.favourite.ui.store.FavouriteScreenState
import com.stslex.feature.favourite.ui.store.FavouriteStore
import com.stslex.feature.favourite.ui.store.FavouriteStore.Action

@Composable
internal fun FavouriteScreen(
    state: FavouriteStore.State,
    onAction: (Action) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        when (state.screen) {
            is FavouriteScreenState.Content -> FavouriteScreenContent(
                state = state.screen,
                items = state.pagingState.result,
                query = state.query,
                isLoading = state.isLoading,
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
        }
    }
}
