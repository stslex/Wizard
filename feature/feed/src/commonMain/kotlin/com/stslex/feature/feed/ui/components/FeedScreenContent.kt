package com.stslex.feature.feed.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.stslex.core.core.Logger
import com.stslex.feature.feed.ui.model.FilmModel
import com.stslex.feature.feed.ui.model.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

@Composable
internal fun FeedScreenContent(
    loadMore: () -> Unit,
    films: ImmutableList<FilmModel>,
    screenState: ScreenState.Content,
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
        val itemHeight = remember(maxWidth, maxHeight) {
            if (maxHeight > maxWidth) {
                maxWidth / 3 * 4
            } else {
                maxHeight / 3 * 4
            }
        }
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState
        ) {
            items(
                count = films.size,
                key = films.key
            ) { index ->
                val film = films.getOrNull(index)
                if (film != null) {
                    FeedScreenFilmItem(
                        film = film,
                        itemHeight = itemHeight
                    )
                }
            }

            if (screenState is ScreenState.Content.AppendLoading) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private val ImmutableList<FilmModel>.key: ((Int) -> Any)?
    get() = if (isEmpty()) {
        null
    } else {
        { index ->
            get(index).id
        }
    }