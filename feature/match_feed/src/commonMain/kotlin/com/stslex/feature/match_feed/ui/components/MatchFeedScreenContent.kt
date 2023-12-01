package com.stslex.feature.match_feed.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.core.core.Logger
import com.stslex.feature.match_feed.ui.model.FilmUi
import com.stslex.feature.match_feed.ui.store.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun MatchFeedScreenContent(
    loadMore: () -> Unit,
    films: ImmutableList<FilmUi>,
    screenState: ScreenState.Content,
    onFilmClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState, films.size) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .filterNotNull()
            .distinctUntilChanged()
            .collectLatest { index ->
                if (
                    screenState != ScreenState.Content.AppendLoading
                    && index >= films.size - 5
                ) {
                    Logger.debug("index: $index, films.size: ${films.size}")
                    loadMore()
                }
            }
    }

    BoxWithConstraints {
        val screenWidth = remember(maxWidth) { maxWidth }
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            flingBehavior = rememberSnapFlingBehavior(listState)
        ) {
            items(
                count = films.size,
                key = films.key,
                contentType = {
                    "film"
                }
            ) { index ->
                val film = films.getOrNull(index)
                if (film != null) {
                    MatchFeedScreenFilmItem(
                        film = film,
                        screenWidth = screenWidth,
                        onFilmClick = onFilmClick,
                        listState = listState,
                    )
                }
            }

            if (screenState is ScreenState.Content.AppendLoading) {
                item(
                    contentType = {
                        "loading"
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private val ImmutableList<FilmUi>.key: ((Int) -> Any)?
    get() = if (isEmpty()) {
        null
    } else {
        { index ->
            get(index).uuid
        }
    }