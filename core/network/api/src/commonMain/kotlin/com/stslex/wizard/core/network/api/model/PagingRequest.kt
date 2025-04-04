package com.stslex.wizard.core.network.api.model

data class PagingRequest(
    val uuid: String,
    val page: Int,
    val pageSize: Int,
    val query: String = "",
)
