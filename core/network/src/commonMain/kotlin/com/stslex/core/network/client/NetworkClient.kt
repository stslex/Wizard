package com.stslex.core.network.client

import io.ktor.client.HttpClient

interface NetworkClient {

    suspend fun <T> request(request: suspend HttpClient.() -> T): T
}
