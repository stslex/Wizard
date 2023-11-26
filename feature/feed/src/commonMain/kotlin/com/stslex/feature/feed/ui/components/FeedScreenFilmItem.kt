package com.stslex.feature.feed.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.feed.ui.model.FilmModel

@Composable
internal fun FeedScreenFilmItem(
    modifier: Modifier = Modifier,
    itemHeight: Dp,
    film: FilmModel
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .padding(AppDimension.medium)
    ) {
        FeedItemFilmPreview(
            modifier = Modifier.fillMaxSize(),
            url = film.imageUrl,
            description = film.title
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(AppDimension.medium),
            text = film.title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(AppDimension.medium),
            text = film.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun FeedItemFilmPreview(
    modifier: Modifier = Modifier,
    url: String,
    description: String
) {
    NetworkImage(
        modifier = modifier,
        url = url,
        contentDescription = description,
        contentScale = ContentScale.Crop,
        onLoading = { progress ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppDimension.large),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        onFailure = { error ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppDimension.large),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error"
                    )
                    Spacer(modifier = Modifier.height(AppDimension.medium))
                    Text(
                        text = error.message ?: "Error",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    )
}