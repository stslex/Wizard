package com.stslex.wizard.core.ui.pager.pager

import com.stslex.wizard.core.core.coroutine.AppCoroutineScope
import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.wizard.core.core.paging.PagingResponse
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingItem
import com.stslex.core.ui.pager.mapper.PagingMapper

interface StorePagerFactory {

    fun <T : PagingItem, R : PagingCoreItem> create(
        scope: AppCoroutineScope,
        request: suspend (page: Int, pageSize: Int) -> PagingResponse<R>,
        mapper: PagingMapper<R, T>,
        config: PagingConfig
    ): StorePager<T>
}