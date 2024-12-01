package com.stslex.wizard.core.ui.kit.pager.paging_worker

import com.stslex.wizard.core.core.coroutine.AppCoroutineScope

class PagingWorkerFactoryImpl : PagingWorkerFactory {

    override fun create(
        scope: AppCoroutineScope,
        delay: Long,
        defaultLoadSize: Int,
        queryLoadSize: Int
    ): PagingWorker = PagingWorkerImpl(
        scope = scope,
        delay = delay,
        defaultLoadSize = defaultLoadSize,
        queryLoadSize = queryLoadSize
    )
}