package com.stslex.core.network.client

import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.client.NetworkClientBuilder.setupDefaultRequest
import com.stslex.core.network.client.NetworkClientBuilder.setupLogging
import com.stslex.core.network.client.NetworkClientBuilder.setupNegotiation
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import kotlinx.coroutines.withContext

class NetworkClientImpl(
    private val appDispatcher: AppDispatcher
) : NetworkClient {

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        request(client)
    }

    private val client: HttpClient = HttpClient(CIO) {
        setupNegotiation()
        install(HttpCache)// TODO Not working?
        setupLogging()
        expectSuccess = true
        setupDefaultRequest()
    }
}
