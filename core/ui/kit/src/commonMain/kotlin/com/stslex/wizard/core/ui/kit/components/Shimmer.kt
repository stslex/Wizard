package com.stslex.wizard.core.ui.kit.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath

@Composable
fun Modifier.shimmer(
    highlightColor: Color = ShimmerDefaults.highlightColor,
    backgroundColor: Color = ShimmerDefaults.backgroundColor,
    durationMs: Int = ShimmerDefaults.DURATION_MS,
): Modifier {
    val infiniteTransition = rememberInfiniteTransition("shimmer")
    val translateAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMs,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Shimmer loading animation",
    )

    val colorStops = listOf(
        0f to backgroundColor,
        translateAnimation.value to highlightColor,
        1f to backgroundColor
    ).toTypedArray()

    return drawWithCache {
        val path = Path().apply { addRect(size.toRect()) }
        onDrawWithContent {
            clipPath(path) {
                drawRect(
                    brush = Brush.linearGradient(
                        colorStops = colorStops,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                    ),
                )
            }
        }
    }
}


data object ShimmerDefaults {

    const val DURATION_MS = 1000

    val highlightColor: Color
        @Composable
        @ReadOnlyComposable
        get() = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant

    val backgroundColor
        @Composable
        @ReadOnlyComposable
        get() = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant
}
