package com.stslex.core.ui.base.image

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
//    when(val painter = asyncPainterResource(url)){
//        is Resource.Failure -> TODO()
//        is Resource.Loading -> TODO()
//        is Resource.Success -> TODO()
//    }
    KamelImage(
        modifier = modifier,
        resource = asyncPainterResource(data = url),
        contentDescription = contentDescription,
        contentScale = contentScale,
        onLoading = onLoading,
        onFailure = onFailure
    )
}