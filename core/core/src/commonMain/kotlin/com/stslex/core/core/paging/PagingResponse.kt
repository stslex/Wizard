package com.stslex.core.core.paging

import com.stslex.core.core.asyncMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<out T : PagingCoreItem>(
    @SerialName("page")
    override val page: Int,
    @SerialName("page_size")
    override val pageSize: Int,
    @SerialName("total")
    override val total: Int,
    @SerialName("has_more")
    override val hasMore: Boolean,
    @SerialName("result")
    override val result: List<T>,
) : PagingCoreData<T>

suspend fun <T : PagingCoreItem, R : PagingCoreItem> PagingResponse<T>.pagingMap(
    transform: suspend (T) -> R,
): PagingResponse<R> = PagingResponse(
    page = page,
    pageSize = pageSize,
    total = total,
    hasMore = hasMore,
    result = result.asyncMap {
        transform(it)
    },
)