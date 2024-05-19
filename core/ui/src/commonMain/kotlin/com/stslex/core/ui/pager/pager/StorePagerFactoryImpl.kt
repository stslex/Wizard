package com.stslex.core.ui.pager.pager

import com.stslex.core.core.coroutine.AppCoroutineScope
import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingItem
import com.stslex.core.ui.pager.mapper.PagingMapper
import com.stslex.core.ui.pager.paging_worker.PagingWorkerFactory

class StorePagerFactoryImpl(
    private val workerFactory: PagingWorkerFactory
) : StorePagerFactory {

    override fun <T : PagingItem, R : PagingCoreItem> create(
        scope: AppCoroutineScope,
        request: suspend (page: Int, pageSize: Int) -> PagingResponse<R>,
        mapper: PagingMapper<R, T>,
        config: PagingConfig
    ): StorePager<T> {
        return StorePagerImpl(
            pagingWorker = workerFactory.create(
                scope = scope,
                delay = config.delay,
                defaultLoadSize = config.defaultLoadSize,
                queryLoadSize = config.queryLoadSize
            ),
            request = request,
            mapper = mapper,
            pagingConfig = config
        )
    }
}
