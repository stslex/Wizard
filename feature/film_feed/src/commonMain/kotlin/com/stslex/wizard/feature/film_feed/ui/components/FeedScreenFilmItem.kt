package com.stslex.wizard.feature.film_feed.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.stslex.wizard.core.ui.image.AppImage
import com.stslex.wizard.core.ui.kit.base.onClickDelay
import com.stslex.wizard.core.ui.kit.theme.AppDimension
import com.stslex.wizard.feature.film_feed.ui.model.FilmModel
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FeedScreenFilmItem(
    modifier: Modifier = Modifier,
    itemHeight: Dp,
    film: FilmModel,
    onFilmClick: (String) -> Unit,
) {
    val posterWidth = remember(itemHeight) {
        (itemHeight - AppDimension.Padding.medium * 2) / 4 * 3
    }

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .padding(AppDimension.Padding.medium),
        shape = RoundedCornerShape(AppDimension.Radius.medium),
        onClick = onClickDelay {
            onFilmClick(film.id)
        }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box {
                FeedItemFilmPreview(
                    modifier = Modifier.width(posterWidth),
                    url = film.imageUrl,
                    description = film.title
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(AppDimension.Padding.small)
                        .background(
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(AppDimension.Radius.small),
                        )
                        .padding(AppDimension.Padding.small),
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