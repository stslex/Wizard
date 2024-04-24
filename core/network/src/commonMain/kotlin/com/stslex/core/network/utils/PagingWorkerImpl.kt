package com.stslex.core.network.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class PagingWorkerImpl : PagingWorker {

    private var job: Job? = null
    private var nextPageJob: Job? = null
    private var lastRequestTime = 0L

    override suspend fun invoke(
        request: suspend () -> Unit
    ) {
        if (lastRequestTime + REQUEST_DELAY > currentTimeMs) {
            nextPageJob = startRequest(
                request = request,
                start = CoroutineStart.LAZY
            )
        }
        startRequest(request = request)
    }

    override suspend fun cancel() {
        job?.cancel()
        nextPageJob?.cancel()
    }

    private suspend fun startRequest(
        request: suspend () -> Unit,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): Job = CoroutineScope(coroutineContext)
        .launch(
            start = start
        ) {
            job = nextPageJob
            nextPageJob = null
            request()
        }.apply {
            invokeOnCompletion {
                nextPageJob?.start()
            }
        }

    companion object {
        private const val REQUEST_DELAY = 500L
    }
}