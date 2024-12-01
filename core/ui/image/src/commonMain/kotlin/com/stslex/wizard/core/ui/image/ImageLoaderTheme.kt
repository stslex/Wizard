package com.stslex.wizard.core.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import org.koin.compose.getKoin

internal val ImageLoaderProvider = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}

@Composable
internal fun createImageLoader(): ImageLoader = ImageLoader
    .Builder(LocalPlatformContext.current)
    .components {
        add(KtorNetworkFetcherFactory(getKoin().get<HttpClient>()))
    }
    .build()

val imageProvidedValue: ProvidedValue<*>
    @Composable
    get() = ImageLoaderProvider provides createImageLoader()
