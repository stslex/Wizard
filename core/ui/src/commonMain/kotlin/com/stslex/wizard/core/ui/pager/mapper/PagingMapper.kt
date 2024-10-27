package com.stslex.wizard.core.ui.pager.mapper

import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.core.ui.base.paging.PagingItem

fun interface PagingMapper<T : PagingCoreItem, R : PagingItem> {

    suspend operator fun invoke(item: T): R
}