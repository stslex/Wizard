package com.stslex.wizard.feature.match_feed.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.stslex.wizard.core.ui.base.image.NetworkImage
import com.stslex.wizard.core.ui.base.onClickDelay
import com.stslex.wizard.core.ui.theme.AppDimension
import com.stslex.wizard.feature.match_feed.ui.model.FilmUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlin.math.abs

enum class SwipeDirection {
    LEFT,
    RIGHT,
    NONE
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun MatchFeedScreenFilmItem(
    modifier: Modifier = Modifier,
    screenWidth: Dp,
    film: FilmUi,
    pagerState: PagerState,
    onFilmClick: (String) -> Unit,
    onItemSwiped: (SwipeDirection, String) -> Unit,
) {
    val swipeableState = rememberSwipeableState(SwipeDirection.NONE)

    val posterWidth by remember(screenWidth) {
        derivedStateOf { screenWidth - AppDimension.Padding.large * 2 }
    }
    val posterHeight by remember(posterWidth) {
        derivedStateOf { posterWidth * 1.5f }
    }
    val progress by remember {
        derivedStateOf {
            swipeableState.offset.value / screenWidth.value
        }
    }

    LaunchedEffect(swipeableState, film.uuid) {
        snapshotFlow {
            swipeableState.progress.to.takeIf {
                swipeableState.isAnimationRunning.not() &&
                        swipeableState.progress.fraction == 1f
            }
        }
            .filter {
                it != SwipeDirection.NONE
            }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { value ->
                delay(300)
                onItemSwiped(value, film.uuid)
            }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = modifier
                .graphicsLayer {
                    rotationZ = 15f * progress
                    translationX = swipeableState.offset.value
                    alpha = 1f - abs(progress * 0.5f)
                }
                .width(posterWidth)
                .height(posterHeight)
                .swipeable(
                    state = swipeableState,
                    anchors = mapOf(
                        -screenWidth.value to SwipeDirection.LEFT,
                        screenWidth.value to SwipeDirection.RIGHT,
                        0f to SwipeDirection.NONE,
                    ),
                    orientation = Orientation.Horizontal,
                )
                .animateItemTop(pagerState)
        ) {
            MatchFeedScreenFilmItemContent(
                modifier = Modifier.fillMaxSize(),
                film = film,
                onFilmClick = onFilmClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MatchFeedScreenFilmItemContent(
    modifier: Modifier = Modifier,
    film: FilmUi,
    onFilmClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = film.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(AppDimension.Radius.medium),
            onClick = onClickDelay {
                onFilmClick(film.uuid)
            },
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                FeedItemFilmPreview(
                    modifier = Modifier.fillMaxSize(),
                    url = film.poster,
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
                Spacer(modifier = Modifier.width(AppDimension.Padding.big))
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                        )
                        .padding(AppDimension.Padding.medium)
                ) {
                    Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                    FilmItemGenres(genres = film.genres)
                    Spacer(modifier = Modifier.height(AppDimension.Padding.big))
                    Text(
                        text = film.description,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 4,
                    )
                }
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