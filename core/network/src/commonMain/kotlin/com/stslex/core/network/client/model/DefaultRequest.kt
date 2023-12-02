package com.stslex.core.network.client.model

data class DefaultRequest(
    val hostUrl: String,
    val headers: Map<String, String> = emptyMap()
) {

    companion object {
        val EMPTY = DefaultRequest(
            hostUrl = "",
            headers = emptyMap()
        )
    }
}