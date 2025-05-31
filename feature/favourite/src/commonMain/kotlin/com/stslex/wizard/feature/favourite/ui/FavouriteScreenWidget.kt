package com.stslex.wizard.feature.favourite.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.favourite.mvi.FavouriteScreenState
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.State
import com.stslex.wizard.feature.favourite.ui.components.content.FavouriteScreenContent
import com.stslex.wizard.feature.favourite.ui.components.error.FavouriteScreenError
import com.stslex.wizard.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer

@Composable
internal fun FavouriteScreenWidget(
    state: State,
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
                    onAction(Action.Click.ItemClick(uuid))
                },
                onLikeClick = { uuid ->
                    onAction(Action.Click.LikeClick(uuid))
                },
                onSearch = { query ->
                    onAction(Action.InputSearch(query))
                },
                onLoadNext = {
                    onAction(Action.Paging.LoadMore)
                }
            )

            is FavouriteScreenState.Error -> FavouriteScreenError(state = state.screen)
            FavouriteScreenState.Shimmer -> FavouriteScreenShimmer()
        }
    }
}
