package com.stslex.core.ui.pager.utils

import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.core.paging.PagingResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface PagingWorker {

    fun <T : PagingCoreItem> launch(
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit = {},
        action: suspend CoroutineScope.() -> PagingResponse<T>,
    ): Job

    fun cancel()
}

