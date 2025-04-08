package com.stslex.wizard.feature.favourite.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.favourite.ui.components.content.FavouriteScreenContent
import com.stslex.wizard.feature.favourite.ui.components.error.FavouriteScreenError
import com.stslex.wizard.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer
import com.stslex.wizard.feature.favourite.ui.store.FavouriteScreenState
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action

@Composable
internal fun FavouriteScreenWidget(
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
                pagingState = state.paging,
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
                },
                onLoadNext = {
                    onAction(Action.LoadMore)
                }
            )

            is FavouriteScreenState.Error -> FavouriteScreenError(state = state.screen)
            FavouriteScreenState.Shimmer -> FavouriteScreenShimmer()
        }
    }
}
