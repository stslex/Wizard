package com.stslex.core.network.api.base

import io.ktor.client.HttpClient

interface NetworkClient {

    suspend fun <T> request(request: suspend HttpClient.() -> T): T
}
