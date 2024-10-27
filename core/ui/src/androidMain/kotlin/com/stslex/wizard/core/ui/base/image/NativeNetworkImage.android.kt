package com.stslex.wizard.core.ui.base.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.stslex.wizard.core.core.Logger

@Composable
actual fun NativeNetworkImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        onState = { state ->
            Logger.debug("state: $state", "NativeNetworkImage")
            if (state is AsyncImagePainter.State.Error) {
                Logger.exception(state.result.throwable, "NativeNetworkImage")
            }
        },
        filterQuality = FilterQuality.None
    )
}