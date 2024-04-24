package com.stslex.core.network.utils

interface PagingWorker {

    suspend operator fun invoke(
        request: suspend () -> Unit
    )

    suspend fun cancel()
}

