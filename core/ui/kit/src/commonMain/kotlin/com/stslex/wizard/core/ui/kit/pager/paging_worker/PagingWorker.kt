package com.stslex.wizard.core.ui.kit.pager.paging_worker

import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.wizard.core.core.paging.PagingResponse
import kotlinx.coroutines.CoroutineScope

interface PagingWorker {

    fun <T : PagingCoreItem> launch(
        requestType: PagingRequestType = PagingRequestType.DEFAULT,
        isForceLoad: Boolean = false,
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit = {},
        action: suspend CoroutineScope.() -> PagingResponse<T>,
    )

    fun cancel()
}
