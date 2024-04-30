package com.stslex.core.ui.base.paging

import androidx.compose.runtime.Stable
import com.stslex.core.core.paging.PagingCoreData

@Stable
data class PagingConfig(
    val pageSize: Int,
    val pageOffset: Float = PagingCoreData.DEFAULT_PAGE_OFFSET
) {

    companion object {

        val DEFAULT = PagingConfig(
            pageSize = PagingCoreData.DEFAULT_PAGE_SIZE,
            pageOffset = PagingCoreData.DEFAULT_PAGE_OFFSET
        )
    }
}