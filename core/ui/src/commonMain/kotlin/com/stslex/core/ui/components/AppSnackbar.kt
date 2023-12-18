package com.stslex.core.ui.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppSnackbar(
    type: SnackbarType,
    modifier: Modifier = Modifier,
) {
    val swipeableState = rememberSwipeableState(SnackbarSwipeState.NONE)
    var width by remember { mutableStateOf(0f) }

    Snackbar(
        modifier = modifier
            .swipeable(
                state = swipeableState,
                orientation = Orientation.Horizontal,
                anchors = mapOf(
                    0f to SnackbarSwipeState.LEFT,
                    width * 0.5f to SnackbarSwipeState.RIGHT,
                    width to SnackbarSwipeState.RIGHT
                ),
            )
            .onGloballyPositioned {
                width = it.size.width.toFloat()
            },
        action = {
            Text(text = "OK")
        }
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "info"
            )
            Text(
                modifier = Modifier,
                text = type.message,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

enum class SnackbarSwipeState {
    LEFT,
    RIGHT,
    NONE
}

sealed class SnackbarType(
    val message: String
) {
    data class Error(
        private val mes: String
    ) : SnackbarType(mes)

    data class Success(
        private val mes: String
    ) : SnackbarType(mes)

    data class Info(
        private val mes: String
    ) : SnackbarType(mes)
}