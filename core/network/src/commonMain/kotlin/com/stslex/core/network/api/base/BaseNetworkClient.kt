package com.stslex.core.network.api.base

import com.stslex.core.core.AppDispatcher
import com.stslex.core.network.api.model.DefaultRequest
import com.stslex.core.network.api.base.NetworkClientBuilder.setupDefaultRequest
import com.stslex.core.network.api.base.NetworkClientBuilder.setupLogging
import com.stslex.core.network.api.base.NetworkClientBuilder.setupNegotiation
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import kotlinx.coroutines.withContext

abstract class BaseNetworkClient(
    private val appDispatcher: AppDispatcher,
    defaultRequest: DefaultRequest = DefaultRequest.EMPTY
) : NetworkClient {

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        request(client)
    }

    private val client: HttpClient = HttpClient(CIO) {
        setupNegotiation()
        install(HttpCache)
        setupLogging()
        expectSuccess = true
        setupDefaultRequest(defaultRequest)
    }
}

