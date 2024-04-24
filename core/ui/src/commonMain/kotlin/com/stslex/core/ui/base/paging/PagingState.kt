package com.stslex.core.ui.base.paging

import androidx.compose.runtime.Stable
import com.stslex.core.core.asyncMap
import com.stslex.core.core.paging.PagingCoreData
import com.stslex.core.core.paging.PagingCoreData.Companion.DEFAULT_PAGE
import com.stslex.core.core.paging.PagingCoreData.Companion.DEFAULT_PAGE_SIZE
import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.core.paging.PagingResponse
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Stable
data class PagingState<out T : PagingItem>(
    override val page: Int,
    override val pageSize: Int,
    override val total: Int,
    override val hasMore: Boolean,
    override val result: ImmutableList<T>,
) : PagingCoreData<T> {

    companion object {

        fun <T : PagingItem> default(
            pageSize: Int = DEFAULT_PAGE_SIZE,
        ) = PagingState(
            page = DEFAULT_PAGE,
            pageSize = pageSize,
            total = 0,
            hasMore = true,
            result = persistentListOf<T>(),
        )
    }
}

suspend fun <T : PagingCoreItem, R : PagingItem> PagingResponse<T>.pagingMap(
    transform: suspend (T) -> R,
): PagingState<R> = PagingState(
    page = page.inc(),
    pageSize = pageSize,
    total = total,
    hasMore = hasMore && result.isNotEmpty(),
    result = result.asyncMap { transform(it) }.toImmutableList(),
)