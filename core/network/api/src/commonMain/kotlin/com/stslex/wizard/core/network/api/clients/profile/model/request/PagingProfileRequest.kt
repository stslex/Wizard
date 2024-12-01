package com.stslex.wizard.core.network.api.clients.profile.model.request

data class PagingProfileRequest(
    val query: String = "",
    val page: Int,
    val pageSize: Int,
    val uuid: String? = null,
)
