package com.stslex.core.ui.base.paging

import androidx.compose.runtime.Stable
import com.stslex.core.core.paging.PagingCoreData.Companion.DEFAULT_PAGING_TYPE
import com.stslex.core.core.paging.PagingCoreItem

@Stable
interface PagingItem : PagingCoreItem {

    override val uuid: String

    val contentType: String
        get() = DEFAULT_PAGING_TYPE
}