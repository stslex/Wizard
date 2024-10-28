package com.stslex.wizard.core.ui.base.image

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.stslex.wizard.core.core.Logger
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun KamelNetworkImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    KamelImage(
        modifier = modifier,
        resource = asyncPainterResource(data = url),
        contentDescription = contentDescription,
        contentScale = contentScale,
        onLoading = onLoading,
        onFailure = { throwable ->
            Logger.e(throwable)
            onFailure?.let { onFailure ->
                onFailure(throwable)
            }
        }
    )
}