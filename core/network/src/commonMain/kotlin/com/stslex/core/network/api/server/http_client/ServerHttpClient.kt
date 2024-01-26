package com.stslex.core.network.api.server.http_client

import io.ktor.client.HttpClient

interface ServerHttpClient {

    val client: HttpClient

    val authClient: HttpClient
}