package com.stslex.core.network.client.base

import io.ktor.client.HttpClient

interface NetworkClient {

    suspend fun <T> request(request: suspend HttpClient.() -> T): T
}
