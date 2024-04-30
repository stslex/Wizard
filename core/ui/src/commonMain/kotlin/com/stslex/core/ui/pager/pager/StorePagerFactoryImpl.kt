package com.stslex.core.ui.pager.pager

import com.stslex.core.core.coroutine.AppCoroutineScope
import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingItem
import com.stslex.core.ui.pager.utils.PagingMapper
import com.stslex.core.ui.pager.utils.PagingWorkerImpl

class StorePagerFactoryImpl : StorePagerFactory {

    override fun <T : PagingItem, R : PagingCoreItem> create(
        scope: AppCoroutineScope,
        request: suspend (page: Int, pageSize: Int) -> PagingResponse<R>,
        mapper: PagingMapper<R, T>,
        config: PagingConfig
    ): StorePager<T> {
        return StorePagerImpl(
            pagingWorker = PagingWorkerImpl(scope = scope),
            request = request,
            mapper = mapper,
            pagingConfig = config
        )
    }
}