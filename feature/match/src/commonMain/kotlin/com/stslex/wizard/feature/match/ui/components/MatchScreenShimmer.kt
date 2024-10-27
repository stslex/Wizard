package com.stslex.wizard.feature.match.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.match.ui.model.MatchUiModel

@Composable
internal fun MatchScreenShimmer(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                count = 15
            ) {
                MatchItem(
                    isShimmer = true,
                    onItemClicked = { },
                    item = MatchUiModel.EMPTY
                )
            }
        }
        Text(text = "Shimmer")
    }
}