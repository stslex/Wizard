package com.stslex.core.ui.base.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun NativeNetworkImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier
) {
    // TODO implement native loader
    KamelNetworkImage(
        url = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}