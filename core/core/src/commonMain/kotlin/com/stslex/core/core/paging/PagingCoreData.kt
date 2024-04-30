package com.stslex.core.core.paging

interface PagingCoreData<out T : PagingCoreItem> {
    val page: Int
    val pageSize: Int
    val total: Int
    val hasMore: Boolean
    val result: List<T>

    companion object {

        const val DEFAULT_PAGE_SIZE = 15
        const val DEFAULT_PAGE = 0
        const val DEFAULT_PAGE_OFFSET = 0.5f
        const val DEFAULT_PAGING_TYPE = "ItemPaging"
        const val DEFAULT_APPEND_TYPE = "AppendPaging"
        const val DEFAULT_BOTTOM_TYPE = "BottomPaging"
    }
}
