package com.stslex.core.ui.base.subcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize

@Composable
fun DimensionSubComposeLayout(
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    dependentContent: @Composable (DpSize) -> Unit
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints: Constraints ->
        val mainPlaceable = subcompose(SubComposeType.MAIN, mainContent)
            .map { measurable -> measurable.measure(constraints.copy(0, 0)) }
            .firstOrNull()
        val dependentPlaceable = subcompose(SubComposeType.DEPEND) {
            val dpSize = mainPlaceable?.let { placeable ->
                DpSize(
                    width = placeable.width.toDp(),
                    height = placeable.height.toDp()
                )
            } ?: DpSize.Zero
            dependentContent(dpSize)
        }
            .map { measurable -> measurable.measure(constraints) }
            .firstOrNull()

        layout(
            width = mainPlaceable?.width ?: 0,
            height = mainPlaceable?.height ?: 0
        ) {
            dependentPlaceable?.placeRelative(0, 0)
        }
    }
}
