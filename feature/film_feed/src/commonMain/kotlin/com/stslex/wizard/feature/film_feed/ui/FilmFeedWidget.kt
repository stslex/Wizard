package com.stslex.wizard.feature.film_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.film_feed.ui.components.FeedScreenContent
import com.stslex.wizard.feature.film_feed.ui.components.FeedScreenError
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Action
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.State
import androidx.compose.runtime.State as ComposeState

@Composable
internal fun FilmFeedWidget(
    modifier: Modifier = Modifier,
    state: ComposeState<State>,
    consume: (Action) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        when (val screenState = state.value.screen) {
            is ScreenState.Content -> FeedScreenContent(
                loadMore = { consume(Action.LoadFilms) },
                films = state.value.films,
                screenState = screenState,
                onFilmClick = { consume(Action.Click.FilmClick(it)) },
            )

            is ScreenState.Error -> FeedScreenError(screenState.message)
        }
    }
}
