package com.stslex.wizard.feature.match.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.base.paging.PagingColumn
import com.stslex.wizard.core.ui.base.paging.PagingUiState
import com.stslex.wizard.core.ui.base.shimmerLoadingAnimation
import com.stslex.wizard.core.ui.theme.AppDimension
import com.stslex.wizard.feature.match.ui.model.MatchUiModel
import com.stslex.wizard.feature.match.ui.store.MatchScreenState
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.Action

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MatchScreenContent(
    state: PagingUiState<MatchUiModel>,
    screen: MatchScreenState.Content,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = screen is MatchScreenState.Content.Refresh,
        onRefresh = { onAction(Action.Refresh) },
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullToRefreshState,
                enabled = screen is MatchScreenState.Content,
            ),
    ) {

        PagingColumn(
            pagingState = state,
            onLoadNext = {
                onAction(Action.LoadMore)
            },
            isAppend = screen is MatchScreenState.Content.Append,
            bottomContent = {
                Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            }
        ) { item ->
            MatchItem(
                item = item,
                onItemClicked = { matchUuid ->
                    onAction(Action.OnMatchClick(matchUuid))
                },
            )
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