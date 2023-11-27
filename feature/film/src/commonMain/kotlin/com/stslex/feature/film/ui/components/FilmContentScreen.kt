package com.stslex.feature.film.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
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
import com.stslex.core.ui.base.SwipeScrollConnection
import com.stslex.core.ui.base.SwipeState
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.base.onClickDelay
import com.stslex.core.ui.theme.AppDimension
import com.stslex.core.ui.theme.toDp
import com.stslex.core.ui.theme.toPx
import com.stslex.feature.film.ui.model.Film
import com.stslex.feature.film.ui.store.FilmStoreComponent.Action

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FilmContentScreen(
    film: Film,
    onAction: (Action) -> Unit,
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
            FilmHeader(
                url = film.poster,
                title = film.title,
                modifier = Modifier
                    .height(headerHeight.toDp)
                    .blur(blurRadius),
            )
            FilmBody(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(
                        y = swipeableState.offset.value
                            .coerceAtLeast(toolbarHeight.toFloat())
                            .toDp - AppDimension.Padding.large * progress
                    )
                    .clip(
                        RoundedCornerShape(
                            topStart = AppDimension.Padding.large * progress,
                            topEnd = AppDimension.Padding.large * progress,
                        )
                    )
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        top = AppDimension.Padding.large * (1 - progress)
                    ),
                description = film.description,
                lazyListState = lazyListState
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
                    ),
                title = film.title,
                onBackClick = {
                    onAction(Action.BackButtonClick)
                },
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
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
        )
    }
}

@Composable
internal fun FilmBody(
    description: String,
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

        items(20) {
            Text(
                modifier = Modifier.padding(
                    bottom = AppDimension.Padding.medium
                ),
                text = description,
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
    NetworkImage(
        modifier = modifier
            .fillMaxWidth(),
        url = url,
        contentDescription = title,
        contentScale = ContentScale.Crop,
    )
}
