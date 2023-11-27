package com.stslex.core.network.utils

import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

object AppKamelConfig {

    val KamelLoggingConfig: KamelConfig
        get() = KamelConfig {
            takeFrom(KamelConfig.Default)
            httpFetcher {
                install(Logging) {
                    logger = KtorLogger
                    level = LogLevel.ALL
                }
            }
        }
}