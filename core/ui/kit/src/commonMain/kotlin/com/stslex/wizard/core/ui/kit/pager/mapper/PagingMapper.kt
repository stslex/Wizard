package com.stslex.wizard.core.ui.kit.pager.mapper

import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.wizard.core.ui.kit.base.paging.PagingItem

fun interface PagingMapper<T : PagingCoreItem, R : PagingItem> {

    suspend operator fun invoke(item: T): R
}