package com.stslex.wizard.feature.film_feed.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.kit.pager.PagingUIList
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import com.stslex.wizard.feature.film_feed.ui.model.ScreenState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

@Composable
internal fun FeedScreenContent(
    loadMore: () -> Unit,
    films: PagingUIList<FilmModel>,
    screenState: ScreenState.Content,
    onFilmClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { index ->
                if (
                    screenState != ScreenState.Content.AppendLoading &&
                    index >= listState.layoutInfo.totalItemsCount - 3
                ) {
                    loadMore()
                }
            }
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = listState,
        userScrollEnabled = screenState !is ScreenState.Content.Shimmer,
    ) {
        items(
            count = films.sizeOrHold,
            key = films.key,
            contentType = { "film" }
        ) { index ->
            val film = films[index] ?: FilmModel.Empty
            FeedScreenFilmItemShimmerable(
                film = film,
                isLoading = screenState is ScreenState.Content.Shimmer,
                onFilmClick = onFilmClick,
            )
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