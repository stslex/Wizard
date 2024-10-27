package com.stslex.wizard.core.ui.pager.paging_worker

import com.stslex.wizard.core.core.coroutine.AppCoroutineScope

interface PagingWorkerFactory {

    fun create(
        scope: AppCoroutineScope,
        delay: Long,
        defaultLoadSize: Int,
        queryLoadSize: Int
    ): PagingWorker
}

