package com.stslex.wizard.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.stslex.wizard.core.network.utils.currentTimeMs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private class Clicker(
    private val throttle: Long = 500
) {

    private var lastClickTime = 0L

    fun click(onClick: () -> Unit) {
        val currentTime = currentTimeMs
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
            coroutineScope.launch(Dispatchers.Default) {
                delay(delay)
                clicker.click(onClick = onClick)
            }
        }
    }
}
