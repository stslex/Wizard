package com.stslex.wizard.core.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter

@Composable
fun AppImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    val painter = rememberAsyncImagePainter(
        model = url,
        imageLoader = ImageLoaderProvider.current,
    )
    // todo add states
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}
