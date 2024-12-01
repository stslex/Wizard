package com.stslex.wizard.core.ui.kit.pager.paging_worker

import com.stslex.wizard.core.core.coroutine.AppCoroutineScope
import com.stslex.wizard.core.ui.kit.pager.paging_worker.PagingWorker

interface PagingWorkerFactory {

    fun create(
        scope: AppCoroutineScope,
        delay: Long,
        defaultLoadSize: Int,
        queryLoadSize: Int
    ): PagingWorker
}

