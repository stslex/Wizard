package com.stslex.core.network.api.base

import com.stslex.core.network.api.base.NetworkClient.Companion.PARAMETER_PAGE
import com.stslex.core.network.api.base.NetworkClient.Companion.PARAMETER_PAGE_SIZE
import com.stslex.core.network.api.base.NetworkClient.Companion.PARAMETER_QUERY
import com.stslex.core.network.api.base.NetworkClient.Companion.PARAMETER_UUID
import com.stslex.core.network.model.PagingRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

interface NetworkClient {

    suspend fun <T> request(request: suspend HttpClient.() -> T): T

    companion object {
        internal const val PARAMETER_QUERY = "query"
        internal const val PARAMETER_PAGE = "page"
        internal const val PARAMETER_PAGE_SIZE = "page_size"
        internal const val PARAMETER_UUID = "uuid"
    }
}

internal suspend inline fun <reified T> NetworkClient.get(
    urlString: String = "",
    crossinline builder: suspend HttpRequestBuilder.() -> Unit
): T = request {
    get(urlString = urlString) {
        builder()
    }.body()
}

internal suspend inline fun <reified T> NetworkClient.post(
    urlString: String = "",
    crossinline builder: suspend HttpRequestBuilder.() -> Unit
): T = request {
    post(urlString = urlString) {
        builder()
    }.body()
}

internal fun HttpRequestBuilder.requestPaging(request: PagingRequest) {
    parameter(PARAMETER_UUID, request.uuid)
    parameter(PARAMETER_QUERY, request.query)
    parameter(PARAMETER_PAGE, request.page)
    parameter(PARAMETER_PAGE_SIZE, request.pageSize)
}