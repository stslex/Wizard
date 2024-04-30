package com.stslex.feature.favourite.ui.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.core.ui.base.paging.PagingColumn
import com.stslex.core.ui.base.paging.PagingUiState
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.favourite.ui.components.empty.FavouriteScreenEmpty
import com.stslex.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer
import com.stslex.feature.favourite.ui.model.FavouriteModel
import com.stslex.feature.favourite.ui.store.FavouriteScreenState

@Composable
internal fun FavouriteScreenContent(
    state: FavouriteScreenState.Content,
    pagingState: PagingUiState<FavouriteModel>,
    query: String,
    isLoading: Boolean,
    onSearch: (String) -> Unit,
    onItemClick: (String) -> Unit,
    onLikeClick: (String) -> Unit,
    onLoadNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column {
            FavouriteScreenSearchField(
                query = query,
                onSearch = onSearch,
            )
            when (state) {
                FavouriteScreenState.Empty -> {
                    Column {
                        if (isLoading) {
                            FavouriteScreenShimmer()
                        } else {
                            FavouriteScreenEmpty(
                                modifier = Modifier
                                    .padding(AppDimension.Padding.medium)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }

                FavouriteScreenState.Content.Data -> {
                    PagingColumn(
                        pagingState = pagingState,
                        onLoadNext = onLoadNext,
                        isAppend = state is FavouriteScreenState.Content.Loading,
                        item = { item ->
                            FavouriteScreenContentItem(
                                item = item,
                                onItemClick = onItemClick,
                                onLikeClick = onLikeClick
                            )
                        },
                        bottomContent = {
                            Spacer(
                                modifier = Modifier.height(AppDimension.Padding.medium)
                            )
                        }
                    )
                }

                FavouriteScreenState.Content.Loading -> {
//                    TODO()
                }

                FavouriteScreenState.Content.Refresh -> {
//                    TODO()
                }
            }
        }
    }
}
