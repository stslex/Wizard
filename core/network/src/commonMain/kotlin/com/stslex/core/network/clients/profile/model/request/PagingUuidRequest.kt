package com.stslex.core.network.clients.profile.model.request

data class PagingRequest(
    val query: String = "",
    val page: Int,
    val pageSize: Int,
    val uuid: String? = null,
)
