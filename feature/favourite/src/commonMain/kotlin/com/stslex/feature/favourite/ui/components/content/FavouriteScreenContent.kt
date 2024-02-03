package com.stslex.feature.favourite.ui.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.favourite.ui.components.empty.FavouriteScreenEmpty
import com.stslex.feature.favourite.ui.components.shimmer.FavouriteScreenShimmer
import com.stslex.feature.favourite.ui.model.FavouriteModel
import com.stslex.feature.favourite.ui.store.FavouriteScreenState
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FavouriteScreenContent(
    state: FavouriteScreenState.Content,
    items: ImmutableList<FavouriteModel>,
    query: String,
    isLoading: Boolean,
    onSearch: (String) -> Unit,
    onItemClick: (String) -> Unit,
    onLikeClick: (String) -> Unit,
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
                FavouriteScreenState.Content.Empty -> {
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(AppDimension.Padding.medium),
                    ) {
                        items(
                            count = items.size,
                            key = { index -> items[index].uuid },
                            contentType = { _ -> "content" }
                        ) { index ->
                            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                            items.getOrNull(index)?.let { item ->
                                FavouriteScreenContentItem(
                                    item = item,
                                    onItemClick = onItemClick,
                                    onLikeClick = onLikeClick
                                )
                            }
                        }
                        if (isLoading) {
                            item {
                                FavouriteScreenContentLoading(
                                    modifier = Modifier
                                        .padding(AppDimension.Padding.medium)
                                        .fillMaxWidth()
                                )
                            }
                        }
                        item(
                            key = "bottom_padding",
                            contentType = { "bottom_padding" }
                        ) {
                            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                        }
                    }
                }
            }

        }
    }
}
