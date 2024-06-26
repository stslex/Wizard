package com.stslex.feature.film_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.mvi.getStoreTest
import com.stslex.feature.film_feed.ui.components.FeedScreenContent
import com.stslex.feature.film_feed.ui.components.FeedScreenError
import com.stslex.feature.film_feed.ui.components.FeedScreenLoading
import com.stslex.feature.film_feed.ui.model.ScreenState
import com.stslex.feature.film_feed.ui.store.FeedStore
import com.stslex.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.feature.film_feed.ui.store.FeedStore.State

object FeedScreen : Screen {

    @Composable
    override fun Content() {
        val store = getStoreTest<FeedStore>()
        val state by remember { store.state }.collectAsState()
        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    is Event.ErrorSnackBar -> {
                        // TODO show error snackbar
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            store.sendAction(Action.LoadFilms)
        }
        FeedScreen(
            state = state,
            sendAction = store::sendAction
        )
    }
}

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
