package com.stslex.feature.favourite.ui.components.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.favourite.ui.model.FavouriteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavouriteScreenContentItem(
    item: FavouriteModel,
    onItemClick: (String) -> Unit,
    onLikeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppDimension.Padding.medium),
        onClick = { onItemClick(item.uuid) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppDimension.Padding.medium),
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(AppDimension.Padding.medium),
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
            Icon(
                modifier = Modifier
                    .padding(AppDimension.Padding.medium)
                    .clickable {
                        onLikeClick(item.uuid)
                    },
                imageVector = if (item.isFavourite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Like button",
            )
        }
    }
}