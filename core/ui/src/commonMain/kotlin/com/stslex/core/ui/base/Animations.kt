package com.stslex.core.ui.base

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stslex.core.ui.theme.toPx
import kotlin.math.roundToInt

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier = composed {
    val shimmerColors = with(MaterialTheme.colorScheme.surfaceVariant) {
        listOf(
            copy(alpha = 0.3f),
            copy(alpha = 0.5f),
            copy(alpha = 0.7f),
            copy(alpha = 0.5f),
            copy(alpha = 0.3f),
        )
    }

    val transition = rememberInfiniteTransition(label = "shimmer loading animation transition")

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Shimmer loading animation",
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
            end = Offset(x = translateAnimation.value, y = angleOfAxisY),
        ),
    )
}

@Composable
fun DotsPrintAnimation(
    modifier: Modifier = Modifier,
    dotSize: Dp = 10.dp,
    dotsCount: Int = 3,
    spaceSize: Dp = 4.dp,
    delayUnit: Int = 300,
    delayFraction: Float = 1f
) {
    val maxOffset = dotSize.toPx / 3

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun Dot(
        offset: Float
    ) = Spacer(
        Modifier
            .size(dotSize)
            .offset(y = -offset.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    )

    @Composable
    fun animateOffsetWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * dotsCount.inc()
                0f at delay with LinearEasing
                maxOffset at delay + (delayUnit * delayFraction).roundToInt() with LinearEasing
                0f at delay + (delayUnit * 2 * delayFraction).roundToInt()
            }
        )
    )

    Row(
        modifier = modifier.padding(
            top = dotSize
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        for (i in 0 until dotsCount) {
            Dot(animateOffsetWithDelay(i * delayUnit).value)
            if (i != dotsCount - 1) {
                Spacer(Modifier.width(spaceSize))
            }
        }
    }
}
