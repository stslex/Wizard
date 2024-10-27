package com.stslex.wizard.feature.film_feed.ui.components

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
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Composable
internal fun FeedScreenContent(
    loadMore: () -> Unit,
    films: ImmutableList<FilmModel>,
    screenState: ScreenState.Content,
    onFilmClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState, films.size) {
        launch(Dispatchers.Default) {
            snapshotFlow {
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            }
                .filterNotNull()
                .distinctUntilChanged()
                .collect { index ->
                    if (
                        screenState != ScreenState.Content.AppendLoading
                        && index >= films.size - 10
                    ) {
                        loadMore()
                    }
                }
        }

    }
    BoxWithConstraints {
        val itemHeight = remember(maxHeight) { maxHeight / 3 }
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState
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
                    FeedScreenFilmItem(
                        film = film,
                        itemHeight = itemHeight,
                        onFilmClick = onFilmClick,
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