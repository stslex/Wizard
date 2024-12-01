package com.stslex.wizard.core.ui.kit.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

private class Clicker(
    private val throttle: Long = 500
) {

    private var lastClickTime = 0L

    fun click(onClick: () -> Unit) {
        val currentTime = Clock.System.now().epochSeconds
        if (currentTime - lastClickTime < throttle) {
            return
        }
        onClick()
        lastClickTime = currentTime
    }
}

@Composable
fun onClick(
    throttle: Long = 500,
    onClick: () -> Unit
): () -> Unit {
    val clicker = remember(onClick) { Clicker(throttle) }
    return remember(onClick) {
        {
            clicker.click(onClick = onClick)
        }
    }
}

@Composable
fun onClickDelay(
    delay: Long = 200,
    throttle: Long = 500,
    onClick: () -> Unit
): () -> Unit {
    val coroutineScope = rememberCoroutineScope()
    val clicker = remember(onClick) { Clicker(throttle) }
    return remember(onClick) {
        {
            coroutineScope.launch {
                delay(delay)
                clicker.click(onClick = onClick)
            }
        }
    }
}
