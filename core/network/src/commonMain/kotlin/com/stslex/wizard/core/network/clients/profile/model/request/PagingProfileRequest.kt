package com.stslex.wizard.core.network.clients.profile.model.request

data class PagingProfileRequest(
    val query: String = "",
    val page: Int,
    val pageSize: Int,
    val uuid: String? = null,
)
