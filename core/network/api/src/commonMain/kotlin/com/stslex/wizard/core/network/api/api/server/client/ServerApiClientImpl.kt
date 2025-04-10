package com.stslex.wizard.core.network.api.api.server.client

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.network.api.api.server.http_client.ServerHttpClient
import com.stslex.wizard.core.network.api.api.server.model.ErrorRepeatEnd
import io.ktor.client.HttpClient
import kotlinx.coroutines.withContext

class ServerApiClientImpl(
    private val appDispatcher: AppDispatcher,
    private val client: ServerHttpClient
) : ServerApiClient {

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        try {
            request(client.authClient)
        } catch (error: ErrorRepeatEnd) {
            request(client.authClient)
        }
    }
}
