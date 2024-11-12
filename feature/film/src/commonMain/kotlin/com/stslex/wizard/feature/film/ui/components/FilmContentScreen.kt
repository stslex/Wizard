package com.stslex.wizard.feature.film.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.stslex.wizard.core.ui.base.SwipeScrollConnection
import com.stslex.wizard.core.ui.base.SwipeState
import com.stslex.wizard.core.ui.base.image.AppImage
import com.stslex.wizard.core.ui.base.onClick
import com.stslex.wizard.core.ui.base.onClickDelay
import com.stslex.wizard.core.ui.theme.AppDimension
import com.stslex.wizard.core.ui.theme.toDp
import com.stslex.wizard.core.ui.theme.toPx
import com.stslex.wizard.feature.film.ui.model.Film

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FilmContentScreen(
    film: Film,
    onBackClick: () -> Unit,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val swipeableState = rememberSwipeableState(
        initialValue = SwipeState.EXPAND
    )

    val swipeScrollConnection = remember {
        SwipeScrollConnection(
            swipeableState = swipeableState,
            isOnPreFlingAllow = {
                lazyListState.firstVisibleItemScrollOffset == 0
            },
        )
    }

    var toolbarHeight by remember { mutableStateOf(0) }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        val defaultHeight = (maxHeight / 2).toPx

        val progress by derivedStateOf {
            (swipeableState.offset.value / defaultHeight).coerceIn(0f, 1f)
        }

        val headerHeight by derivedStateOf { defaultHeight * progress }
        val blurRadius by derivedStateOf { 12.dp * (1 - progress) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipeableState,
                    anchors = mapOf(
                        0f to SwipeState.COLLAPSE,
                        defaultHeight to SwipeState.EXPAND
                    ),
                    orientation = Orientation.Vertical,
                )
                .nestedScroll(swipeScrollConnection),
        ) {
            Box(
                modifier = Modifier
                    .height(headerHeight.toDp)
            ) {
                FilmHeader(
                    url = film.poster,
                    title = film.title,
                    modifier = Modifier.blur(blurRadius),
                )
                AnimatedContent(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    targetState = derivedStateOf { progress > 0.5f }.value,
                ) { isVisible ->
                    if (isVisible) {
                        FilmActions(
                            isLiked = film.isFavorite,
                            onLikeClick = onLikeClick
                        )
                    }
                }
            }
            FilmBody(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(
                        y = swipeableState.offset.value
                            .coerceAtLeast(toolbarHeight.toFloat())
                            .toDp - AppDimension.Padding.large * 2 * progress
                    )
                    .clip(
                        RoundedCornerShape(
                            topStart = AppDimension.Padding.large * progress,
                            topEnd = AppDimension.Padding.large * progress,
                        )
                    )
                    .background(MaterialTheme.colorScheme.background),
                film = film,
                lazyListState = lazyListState,
            )
            FilmToolbar(
                modifier = Modifier
                    .onSizeChanged { currentSize ->
                        currentSize.height
                            .takeIf { it != toolbarHeight }
                            ?.let { toolbarHeight = it }
                    }
                    .background(
                        MaterialTheme.colorScheme.background.copy(
                            alpha = (1 - progress).coerceAtLeast(0.5f)
                        )
                    )
                    .statusBarsPadding(),
                title = film.title,
                onBackClick = onBackClick,
                textSize = MaterialTheme.typography.displayMedium
                    .fontSize * ((1 - progress).coerceAtLeast(0.5f))
            )
        }
    }
}

@Composable
internal fun FilmToolbar(
    title: String,
    textSize: TextUnit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilmToolbarNavButton(
            onBackClick = onBackClick,
        )
        Spacer(modifier = Modifier.width(AppDimension.Padding.medium))
        Text(
            text = title,
            fontSize = textSize,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
internal fun FilmToolbarNavButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = onClickDelay(
            onClick = onBackClick
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
        )
    }
}

@Composable
internal fun FilmBody(
    film: Film,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(AppDimension.Padding.big),
        state = lazyListState,
    ) {
        item {
            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
        }

        item {
            FilmInfo(
                modifier = Modifier
                    .padding(AppDimension.Padding.medium),
                film = film,
            )
        }

        item {
            Text(
                modifier = Modifier.padding(
                    bottom = AppDimension.Padding.medium
                ),
                text = "Description",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(
                    bottom = AppDimension.Padding.medium
                ),
                text = film.description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        items(20) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
                    .padding(AppDimension.Padding.medium),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Item $it",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
internal fun FilmActions(
    onLikeClick: () -> Unit,
    isLiked: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = AppDimension.Radius.large,
                    bottomStart = AppDimension.Radius.large
                )
            )
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
    ) {

        IconButton(
            modifier = Modifier
                .padding(AppDimension.Padding.smallest),
            onClick = onClick(onClick = onLikeClick)
        ) {
            Icon(
                imageVector = if (isLiked) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Like",
            )
        }
        IconButton(
            modifier = Modifier
                .padding(AppDimension.Padding.smallest),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Back",
            )
        }
        IconButton(
            modifier = Modifier
                .padding(AppDimension.Padding.smallest),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Back",
            )
        }
    }
}

@Composable
internal fun FilmInfo(
    film: Film,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = AppDimension.Padding.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rate: ",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = film.rating,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Director: ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = film.director,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Release date: ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = film.year,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Duration: ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = film.duration,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Country: ",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = film.countries.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
internal fun FilmHeader(
    url: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    AppImage(
        modifier = modifier
            .fillMaxWidth(),
        url = url,
        contentDescription = title,
        contentScale = ContentScale.Crop,
    )
}
