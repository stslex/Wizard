package com.stslex.wizard.core.network.api.api.base

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.network.client.DefaultRequest
import com.stslex.wizard.core.network.client.NetworkClientBuilder.setupDefaultRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.cache.HttpCache
import kotlinx.coroutines.withContext

open class BaseNetworkClient(
    private val appDispatcher: AppDispatcher,
    client: HttpClient,
    defaultRequest: DefaultRequest = DefaultRequest.EMPTY
) : NetworkClient {

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        request(client)
    }

    private val client: HttpClient = client.config {
        install(HttpCache)
        expectSuccess = true
        setupDefaultRequest(defaultRequest)
    }
}

