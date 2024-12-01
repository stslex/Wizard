package com.stslex.wizard.feature.favourite.ui.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.stslex.wizard.core.ui.kit.base.shimmerLoadingAnimation
import com.stslex.wizard.core.ui.kit.theme.AppDimension

@Composable
fun FavouriteScreenShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) {
            items(
                count = 20,
            ) { index ->
                Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimension.Padding.medium)
                        .height(40.dp)
                        .clip(RoundedCornerShape(AppDimension.Radius.medium))
                        .background(MaterialTheme.colorScheme.surface)
                        .shimmerLoadingAnimation()
                )
            }
            item(
                key = "bottom_padding",
                contentType = { "bottom_padding" }
            ) {
                Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            }
        }
    }
}