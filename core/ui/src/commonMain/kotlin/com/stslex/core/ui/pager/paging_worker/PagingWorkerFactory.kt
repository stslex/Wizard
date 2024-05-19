package com.stslex.core.ui.pager.paging_worker

import com.stslex.core.core.coroutine.AppCoroutineScope

interface PagingWorkerFactory {

    fun create(
        scope: AppCoroutineScope,
        delay: Long,
        defaultLoadSize: Int,
        queryLoadSize: Int
    ): PagingWorker
}

