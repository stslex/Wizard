package com.stslex.core.network.clients.profile.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    @SerialName("page")
    val page: Int,
    @SerialName("page_size")
    val pageSize: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("has_more")
    val hasMore: Boolean,
    @SerialName("result")
    val result: List<T>,
)
