package com.stslex.feature.match_feed.ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

fun Modifier.animateItemTop(
    listState: LazyListState,
    key: Any?,
    valueKf: Float = 0.2f
): Modifier = graphicsLayer {
    val position = listState.normalizedPositionTop(key)
    val value = 1 - (position.absoluteValue * valueKf)
    alpha = value
    scaleX = value
    scaleY = value
}

fun LazyListState.normalizedPositionTop(
    key: Any?
): Float = with(layoutInfo) {
    visibleItemsInfo.firstOrNull {
        it.key == key
    }?.let {
        1 - (it.size - it.offset.toFloat()) / it.size
    } ?: 0F
}