package com.stslex.wizard.core.ui.image

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.compose.LocalPlatformContext
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import org.koin.compose.getKoin

object AppImageLoader {

    @Composable
    fun init() {
        val context = LocalPlatformContext.current
        val networkFactory = KtorNetworkFetcherFactory(getKoin().get<HttpClient>())
        SingletonImageLoader.setSafe {
            ImageLoader
                .Builder(context)
                .components {
                    add(networkFactory)
                }
                .memoryCache {
                    MemoryCache.Builder()
                        .maxSizePercent(context)
                        .build()
                }
                .build()
        }
    }
}