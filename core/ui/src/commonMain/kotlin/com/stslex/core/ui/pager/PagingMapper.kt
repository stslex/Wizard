package com.stslex.core.ui.pager

import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.ui.base.paging.PagingItem

fun interface PagingMapper<T : PagingCoreItem, R : PagingItem> {

    suspend operator fun invoke(item: T): R
}