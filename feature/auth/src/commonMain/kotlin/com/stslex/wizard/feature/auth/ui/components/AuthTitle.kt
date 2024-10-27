package com.stslex.wizard.feature.auth.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.auth.ui.store.AuthStoreComponent.AuthFieldsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun AuthTitle(
    swipeableState: SwipeableState<AuthFieldsState>,
    modifier: Modifier = Modifier
) {
    val progress = swipeableState.progress.fraction
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppDimension.Padding.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthFieldsState.entries.forEach { item ->
            val scaleValue by animateFloatAsState(
                targetValue = if (item == swipeableState.progress.to) {
                    (progress * 1.5f)
                } else {
                    (1 - progress)
                }.coerceIn(.6f, 1f)
            )
            val scaleDividerValue by animateFloatAsState(
                targetValue = if (item == swipeableState.progress.to) {
                    (progress * 1.5f)
                } else {
                    (1 - progress)
                }.coerceIn(.1f, 1f)
            )
            val coroutineScope = rememberCoroutineScope()
            Column(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            coroutineScope.launch {
                                swipeableState.animateTo(item)
                            }
                        }
                    )
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.scale(scaleValue),
                    text = item.titleRes,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = scaleValue
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = AppDimension.Padding.small)
                        .width(100.dp * scaleDividerValue)
                        .clip(RoundedCornerShape(AppDimension.Padding.smallest)),
                    thickness = 4.dp * scaleValue,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = scaleValue
                    )
                )
            }

        }
    }
}
