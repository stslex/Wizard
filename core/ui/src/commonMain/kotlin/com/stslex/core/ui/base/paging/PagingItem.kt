package com.stslex.core.ui.base.paging

import androidx.compose.runtime.Stable
import com.stslex.core.core.paging.PagingCoreItem

@Stable
interface PagingItem : PagingCoreItem {
    override val uuid: String
}