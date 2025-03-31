package com.stslex.wizard.core.ui.image

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun AppImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    placeholder: Painter? = null,
    clipToBounds: Boolean = true,
    alignment: Alignment = Alignment.Center,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = placeholder,
        clipToBounds = clipToBounds,
        alignment = alignment,
        onLoading = {},
        onError = {},
        onSuccess = {}
    )
}
