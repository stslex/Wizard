package com.stslex.feature.match_feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.feature.match_feed.ui.components.MatchFeedScreenContent
import com.stslex.feature.match_feed.ui.components.MatchFeedScreenError
import com.stslex.feature.match_feed.ui.components.MatchFeedScreenLoading
import com.stslex.feature.match_feed.ui.store.MatchFeedStore
import com.stslex.feature.match_feed.ui.store.MatchFeedStoreComponent.Action
import com.stslex.feature.match_feed.ui.store.MatchFeedStoreComponent.Event.ErrorSnackBar
import com.stslex.feature.match_feed.ui.store.MatchFeedStoreComponent.State
import com.stslex.feature.match_feed.ui.store.ScreenState

object MatchFeedScreen : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<MatchFeedStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    is ErrorSnackBar -> {
                        // TODO show error snackbar
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            store.sendAction(Action.Init)
        }
        MatchFeedScreen(
            state = state,
            sendAction = store::sendAction
        )
    }
}

@Composable
private fun MatchFeedScreen(
    state: State,
    sendAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (val screenState = state.screen) {
            is ScreenState.Content -> MatchFeedScreenContent(
                loadMore = remember { { sendAction(Action.LoadFilms) } },
                films = state.films,
                screenState = screenState,
                onFilmClick = remember { { sendAction(Action.FilmClick(it)) } },
            )

            is ScreenState.Error -> MatchFeedScreenError(screenState.message)
            ScreenState.Loading -> MatchFeedScreenLoading()
        }
    }
}