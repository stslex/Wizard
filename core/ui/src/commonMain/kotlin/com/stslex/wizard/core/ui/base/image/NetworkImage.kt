package com.stslex.wizard.core.ui.base.image

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    imageType: ImageType = ImageType.KAMEL,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    when (imageType) {
        ImageType.NATIVE -> NativeNetworkImage(
            modifier = modifier,
            url = url,
            contentDescription = contentDescription,
            contentScale = contentScale
        )

        ImageType.KAMEL -> KamelNetworkImage(
            modifier = modifier,
            url = url,
            contentDescription = contentDescription,
            contentScale = contentScale,
            onLoading = onLoading,
            onFailure = onFailure
        )
    }
}
