package com.stslex.wizard.feature.match_feed.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.feature.match_feed.ui.model.FilmUi
import com.stslex.wizard.feature.match_feed.ui.store.ScreenState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MatchFeedScreenContent(
    loadMore: () -> Unit,
    films: ImmutableList<FilmUi>,
    screenState: ScreenState.Content,
    onFilmClick: (String) -> Unit,
    onItemSwiped: (SwipeDirection, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { films.size }

    LaunchedEffect(pagerState, films.size) {
        snapshotFlow {
            pagerState.currentPage
        }
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

    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints {
        val screenWidth = remember(maxWidth) { maxWidth }

        VerticalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            key = { page ->
                films.getOrNull(page)?.uuid ?: page
            }
        ) { page ->
            val film = films.getOrNull(page)
            if (film != null) {
                MatchFeedScreenFilmItem(
                    film = film,
                    screenWidth = screenWidth,
                    onFilmClick = onFilmClick,
                    pagerState = pagerState,
                    onItemSwiped = { direction, id ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                            onItemSwiped(direction, id)
                        }
                    }
                )
            }
        }
    }
}
