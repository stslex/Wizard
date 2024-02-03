package com.stslex.feature.favourite.ui.components.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.stslex.core.ui.theme.AppDimension

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
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(AppDimension.Padding.medium),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(AppDimension.Padding.medium),
                            text = "Loading...",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            modifier = Modifier.padding(AppDimension.Padding.medium),
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like button",
                        )
                    }
                }
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