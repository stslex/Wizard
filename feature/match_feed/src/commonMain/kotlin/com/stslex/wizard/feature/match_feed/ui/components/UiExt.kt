package com.stslex.wizard.feature.match_feed.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.animateItemTop(
    pagerState: PagerState,
    valueKf: Float = 0.2f
): Modifier = graphicsLayer {
    val position = pagerState.currentPageOffsetFraction
    val value = 1 - (position.absoluteValue * valueKf)
    alpha = value
    scaleX = value
    scaleY = value
}