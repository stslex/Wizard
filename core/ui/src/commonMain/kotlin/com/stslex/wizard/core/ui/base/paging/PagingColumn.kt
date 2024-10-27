package com.stslex.wizard.core.ui.base.paging

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.core.paging.PagingCoreData.Companion.DEFAULT_APPEND_TYPE
import com.stslex.wizard.core.core.paging.PagingCoreData.Companion.DEFAULT_BOTTOM_TYPE
import com.stslex.core.ui.base.DotsPrintAnimation
import com.stslex.core.ui.theme.AppDimension
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun <Item : PagingItem> PagingColumn(
    pagingState: PagingUiState<Item>,
    onLoadNext: () -> Unit,
    isAppend: Boolean,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    isScreenAllow: Boolean = true,
    appendContent: @Composable () -> Unit = {
        DotsPrintAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.medium),
            dotsCount = 3,
        )
    },
    bottomContent: @Composable () -> Unit = {},
    item: @Composable (item: Item) -> Unit,
) {

    LaunchedEffect(state, pagingState) {
        snapshotFlow {
            state.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .filterNotNull()
            .filter { index ->
                pagingState.hasMore &&
                        index >= (pagingState.items.size - pagingState.pageOffset) &&
                        isScreenAllow
            }
            .distinctUntilChanged()
            .collect {
                onLoadNext()
            }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state
    ) {
        items(
            count = pagingState.items.size,
            key = { index ->
                pagingState.items[index].uuid
            },
            contentType = { index ->
                pagingState.items[index].contentType
            }
        ) { index ->
            pagingState.items
                .getOrNull(index)
                ?.let {
                    item(it)
                }
        }
        if (isAppend) {
            item(
                contentType = DEFAULT_APPEND_TYPE
            ) {
                appendContent()
            }
        }
        item(
            contentType = DEFAULT_BOTTOM_TYPE
        ) {
            bottomContent()
        }
    }
}