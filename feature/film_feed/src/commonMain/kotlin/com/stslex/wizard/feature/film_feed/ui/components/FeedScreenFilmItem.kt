package com.stslex.wizard.feature.film_feed.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.stslex.wizard.core.ui.image.AppImage
import com.stslex.wizard.core.ui.kit.components.shimmer
import com.stslex.wizard.core.ui.kit.theme.AppDimension
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FeedScreenFilmItemShimmerable(
    modifier: Modifier = Modifier,
    film: FilmModel,
    isLoading: Boolean,
    onFilmClick: (String) -> Unit,
) {
    if (isLoading) {
        FeedScreenFilmItem(
            modifier = modifier.shimmer(),
            film = film,
            onFilmClick = onFilmClick,
        )
    } else {
        FeedScreenFilmItem(
            modifier = modifier,
            film = film,
            onFilmClick = onFilmClick,
        )
    }
}

@Composable
private fun FeedScreenFilmItem(
    modifier: Modifier = Modifier,
    film: FilmModel,
    onFilmClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(AppDimension.Padding.medium)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(AppDimension.Radius.medium),
            )
            .clip(RoundedCornerShape(AppDimension.Radius.medium))
            .clickable {
                onFilmClick(film.id)
            }
            .then(modifier)
    ) {
        Box {
            FeedItemFilmPreview(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp),
                url = film.imageUrl,
                description = film.title
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(AppDimension.Padding.medium)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(AppDimension.Radius.medium),
                    )
                    .padding(AppDimension.Padding.medium),
                text = film.rate,
                style = MaterialTheme.typography.titleSmall,
            )
        }

        Spacer(modifier = Modifier.width(AppDimension.Padding.big))
        Column {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            FilmItemGenres(genres = film.genres)
        }
    }
}

// TODO: FilmItemGenres
@Composable
fun FilmItemGenres(
    genres: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = genres.joinToString(separator = ", "),
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
internal fun FeedItemFilmPreview(
    modifier: Modifier = Modifier,
    url: String,
    description: String
) {
    AppImage(
        modifier = modifier,
        url = url,
        contentDescription = description,
        contentScale = ContentScale.Crop,
        onLoading = { progress ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppDimension.Padding.big),
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
                    .padding(AppDimension.Padding.medium),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error"
                    )
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    Text(
                        text = error.message ?: "Error",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    )
}