package com.stslex.feature.feed.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.feed.ui.model.FilmModel
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun FeedScreenFilmItem(
    modifier: Modifier = Modifier,
    itemHeight: Dp,
    film: FilmModel,
    onFilmClick: (String) -> Unit,
    onGenreClick: (String) -> Unit,
) {
    val posterWidth = remember(itemHeight) {
        (itemHeight - AppDimension.Padding.medium * 2) / 4 * 3
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .padding(AppDimension.Padding.medium)
            .clickable {
                onFilmClick(film.id)
            }
    ) {
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
            FilmItemGenres(genres = film.genres, onItemClick = onGenreClick)
            Spacer(modifier = Modifier.height(AppDimension.Padding.big))
            Text(
                modifier = Modifier.fillMaxSize(),
                text = film.description,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// TODO: FilmItemGenres
@Composable
fun FilmItemGenres(
    genres: ImmutableList<String>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
) {
    val style = MaterialTheme.typography.labelSmall
        .copy(color = MaterialTheme.colorScheme.onBackground)
        .toSpanStyle()

    val annotated = buildAnnotatedString {
        genres.forEachIndexed { index, genre ->
            withStyle(style) {
                append(genre)
                addStringAnnotation(
                    tag = genre,
                    annotation = genre,
                    start = length - genre.length,
                    end = length
                )
            }

            if (index != genres.lastIndex) {
                append(", ")
            }
        }
    }

    ClickableText(
        modifier = modifier,
        text = annotated,
        onClick = { offset ->
            annotated.getStringAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                onItemClick(annotation.tag)
            }
        },
    )
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