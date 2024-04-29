package com.stslex.feature.match.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.core.ui.base.DotsPrintAnimation
import com.stslex.core.ui.base.paging.PagingState
import com.stslex.core.ui.base.shimmerLoadingAnimation
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.match.ui.model.MatchUiModel
import com.stslex.feature.match.ui.store.MatchScreenState
import com.stslex.feature.match.ui.store.MatchStore.Action

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MatchScreenContent(
    state: PagingState<MatchUiModel>,
    screen: MatchScreenState.Content,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = screen is MatchScreenState.Content.Refresh,
        onRefresh = { onAction(Action.Refresh) },
    )
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex
        }
            .collect { firstVisibleItemIndex ->
                if (
                    firstVisibleItemIndex >= state.result.size - state.pageSize * 0.5f
                ) {
                    onAction(Action.LoadMore)
                }
            }
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            items(
                count = state.result.size,
                key = { index ->
                    state.result[index].uuid
                },
            ) { index ->
                state.result.getOrNull(index)?.let { item ->
                    MatchItem(
                        item = item,
                        onItemClicked = { matchUuid ->
                            onAction(Action.OnMatchClick(matchUuid))
                        },
                    )
                }
            }
            if (screen is MatchScreenState.Content.Append) {
                item {
                    DotsPrintAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppDimension.Padding.medium),
                        dotsCount = 3,
                    )
                }
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
            refreshing = screen is MatchScreenState.Content.Refresh
        )
    }
}

@Composable
internal fun MatchItem(
    item: MatchUiModel,
    onItemClicked: (matchUuid: String) -> Unit,
    modifier: Modifier = Modifier,
    isShimmer: Boolean = false,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppDimension.Padding.medium)
            .shimmerLoadingAnimation(isShimmer),
        onClick = { onItemClicked(item.uuid) },
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(AppDimension.Padding.medium)
                    .shimmerLoadingAnimation(isShimmer),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(AppDimension.Padding.medium)
                    .shimmerLoadingAnimation(isShimmer),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}