package com.stslex.wizard.feature.favourite.ui.components.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.wizard.core.ui.kit.base.DotsPrintAnimation

@Composable
fun FavouriteScreenContentLoading(
    modifier: Modifier = Modifier
) {
    DotsPrintAnimation(
        modifier = modifier,
        dotsCount = 3,
    )
}