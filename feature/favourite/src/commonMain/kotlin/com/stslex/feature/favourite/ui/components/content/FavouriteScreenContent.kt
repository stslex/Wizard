package com.stslex.feature.favourite.ui.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.favourite.ui.model.FavouriteModel
import com.stslex.feature.favourite.ui.store.FavouriteScreenState
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FavouriteScreenContent(
    state: FavouriteScreenState.Content,
    items: ImmutableList<FavouriteModel>,
    onItemClick: (String) -> Unit,
    onLikeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
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
            item(
                key = "bottom_padding",
                contentType = { "bottom_padding" }
            ) {
                Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            }
        }

        if (state is FavouriteScreenState.Content.Loading) {
            FavouriteScreenContentLoading(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

