package com.stslex.wizard.core.network.client

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