package com.stslex.wizard.feature.match_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.match_feed.ui.components.MatchFeedScreenContent
import com.stslex.wizard.feature.match_feed.ui.components.MatchFeedScreenError
import com.stslex.wizard.feature.match_feed.ui.components.MatchFeedScreenLoading
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.State
import com.stslex.wizard.feature.match_feed.ui.mvi.ScreenState

@Composable
internal fun MatchDetailsScreenWidget(
    state: State,
    sendAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (val screenState = state.screen) {
            is ScreenState.Content -> MatchFeedScreenContent(
                loadMore = { sendAction(Action.Common.LoadFilms) },
                films = state.films,
                screenState = screenState,
                onFilmClick = { sendAction(Action.User.FilmClick(it)) },
                onItemSwiped = { direction, id ->
                    sendAction(Action.User.FilmSwiped(direction = direction, uuid = id))
                },
            )

            is ScreenState.Error -> MatchFeedScreenError(screenState.message)
            ScreenState.Loading -> MatchFeedScreenLoading()
        }
    }
}