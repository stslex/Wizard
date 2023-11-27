package com.stslex.core.network.client

import com.stslex.core.network.utils.KtorLogger
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal object NetworkClientBuilder {

    private const val HOST_URL = "smth_host_url"

    @OptIn(ExperimentalSerializationApi::class)
    fun HttpClientConfig<CIOEngineConfig>.setupNegotiation() {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
    }

    fun HttpClientConfig<CIOEngineConfig>.setupDefaultRequest() {
        defaultRequest {
            url {
                host = HOST_URL
                protocol = URLProtocol.HTTPS
            }
            headers {
                // TODO add auth headers
            }
        }
    }

    fun HttpClientConfig<CIOEngineConfig>.setupLogging() {
        install(Logging) {
            // TODO dev type log
            logger = KtorLogger
            level = LogLevel.ALL
        }
    }
}