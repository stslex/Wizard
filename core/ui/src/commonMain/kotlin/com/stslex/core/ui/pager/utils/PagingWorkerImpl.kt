package com.stslex.core.ui.pager.utils

import com.stslex.core.core.coroutine.AppCoroutineScope
import com.stslex.core.core.paging.PagingCoreItem
import com.stslex.core.core.paging.PagingResponse
import com.stslex.core.network.utils.currentTimeMs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job

class PagingWorkerImpl(
    private val scope: AppCoroutineScope
) : PagingWorker {

    private var job: Job? = null
    private var nextPageJob: Job? = null
    private var lastRequestTime = 0L

    override fun <T : PagingCoreItem> launch(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>
    ): Job = if (lastRequestTime + REQUEST_DELAY > currentTimeMs) {
        startRequest(
            onError = onError,
            onSuccess = onSuccess,
            action = action,
            start = CoroutineStart.LAZY
        ).apply {
            nextPageJob = this
        }
    } else {
        startRequest(
            onError = onError,
            onSuccess = onSuccess,
            action = action,
        )
    }

    override fun cancel() {
        job?.cancel()
        nextPageJob?.cancel()
    }

    private fun <T : PagingCoreItem> startRequest(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): Job = scope
        .launch(
            start = start,
            onError = onError,
            onSuccess = onSuccess,
            action = {
                job = nextPageJob
                nextPageJob = null
                action()
            }
        ).apply {
            invokeOnCompletion {
                nextPageJob?.start()
            }
        }

    companion object {
        private const val REQUEST_DELAY = 500L
    }
}