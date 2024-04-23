package com.stslex.core.network.model

data class PagingRequest(
    val uuid: String,
    val page: Int,
    val pageSize: Int,
    val query: String = "",
)
