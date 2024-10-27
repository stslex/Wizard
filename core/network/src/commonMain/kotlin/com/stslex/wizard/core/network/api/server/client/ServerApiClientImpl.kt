package com.stslex.wizard.core.network.api.server.client

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.core.network.api.server.http_client.ServerHttpClient
import com.stslex.wizard.core.network.api.server.model.ErrorRepeatEnd
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
