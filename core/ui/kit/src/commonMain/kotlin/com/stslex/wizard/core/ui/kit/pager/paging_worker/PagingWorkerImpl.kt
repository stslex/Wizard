package com.stslex.wizard.core.ui.kit.pager.paging_worker

import com.stslex.wizard.core.core.coroutine.AppCoroutineScope
import com.stslex.wizard.core.core.paging.PagingCoreItem
import com.stslex.wizard.core.core.paging.PagingResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class PagingWorkerImpl(
    private val scope: AppCoroutineScope,
    private val delay: Long,
    private val defaultLoadSize: Int,
    private val queryLoadSize: Int
) : PagingWorker {

    private val _jobs: MutableList<Job> = mutableListOf()
    private val jobs: List<Job>
        get() = _jobs.toList()

    override fun <T : PagingCoreItem> launch(
        requestType: PagingRequestType,
        isForceLoad: Boolean,
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>
    ) {
        if (isForceLoad) {
            cancel()
        }
        when (requestType) {
            PagingRequestType.DEFAULT -> startDefaultJob(
                onError = onError,
                onSuccess = onSuccess,
                action = action
            )

            PagingRequestType.QUERY -> startQueryJob(
                onError = onError,
                onSuccess = onSuccess,
                action = action
            )
        }
    }

    private fun <T : PagingCoreItem> startDefaultJob(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>
    ) {
        if (jobs.size >= defaultLoadSize) {
            return
        }
        startNewJob(
            onError = onError,
            onSuccess = onSuccess,
            action = action
        )
    }

    private fun <T : PagingCoreItem> startQueryJob(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>
    ) {
        if (jobs.size >= queryLoadSize) {
            val removeJobs = jobs.subList(1, jobs.size.dec())
            removeJobs.forEach { it.cancel() }
            _jobs.removeAll(removeJobs)
        }
        startNewJob(
            onError = onError,
            onSuccess = onSuccess,
            action = action
        )
    }

    private fun <T : PagingCoreItem> startNewJob(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(PagingResponse<T>) -> Unit,
        action: suspend CoroutineScope.() -> PagingResponse<T>
    ) {
        val newJob = scope.launch(
            start = CoroutineStart.LAZY,
            onError = onError,
            onSuccess = onSuccess,
            action = {
                delay(delay)
                action()
            }
        )
        val lastJob = jobs.lastOrNull()
        if (lastJob == null || lastJob.isActive.not()) {
            lastJob?.let(_jobs::remove)
            newJob.start()
            _jobs.add(newJob)
            return
        }
        lastJob.invokeOnCompletion {
            _jobs.remove(lastJob)
            newJob.start()
            _jobs.add(newJob)
        }
    }

    override fun cancel() {
        _jobs.forEach { it.cancel() }
        _jobs.clear()
    }
}