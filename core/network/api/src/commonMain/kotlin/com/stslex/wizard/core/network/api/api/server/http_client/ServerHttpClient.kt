package com.stslex.wizard.core.network.api.api.server.http_client

import io.ktor.client.HttpClient

interface ServerHttpClient {

    val client: HttpClient

    val authClient: HttpClient
}