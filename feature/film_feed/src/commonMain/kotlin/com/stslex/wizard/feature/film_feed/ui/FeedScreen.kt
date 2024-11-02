package com.stslex.wizard.feature.film_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.film_feed.ui.components.FeedScreenContent
import com.stslex.wizard.feature.film_feed.ui.components.FeedScreenError
import com.stslex.wizard.feature.film_feed.ui.components.FeedScreenLoading
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State

@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
    state: State,
    sendAction: (Action) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (val screenState = state.screen) {
            is ScreenState.Content -> FeedScreenContent(
                loadMore = remember { { sendAction(Action.LoadFilms) } },
                films = state.films,
                screenState = screenState,
                onFilmClick = remember { { sendAction(Action.FilmClick(it)) } },
            )

            is ScreenState.Error -> FeedScreenError(screenState.message)
            ScreenState.Loading -> FeedScreenLoading()
        }
    }
}
