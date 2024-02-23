package com.stslex.core.network.utils

fun interface PagingWorker {

    suspend operator fun invoke(
        request: suspend () -> Unit
    )
}

