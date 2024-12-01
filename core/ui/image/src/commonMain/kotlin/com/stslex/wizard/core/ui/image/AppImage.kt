package com.stslex.wizard.core.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

// todo refactor
private var imageLoader: ImageLoader? = null

@Composable
internal fun rememberImageLoader(): ImageLoader {
    val platformContext = LocalPlatformContext.current
    return remember {
        imageLoader ?: ImageLoader
            .Builder(platformContext)
            .components { add(KtorNetworkFetcherFactory(HttpClient(CIO))) }
            .build()
    }
}

@Composable
fun AppImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
) {
    val imageLoader = rememberImageLoader()
    val painter = rememberAsyncImagePainter(
        model = url,
        imageLoader = imageLoader,
    )
    // todo add states
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}
