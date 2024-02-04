package com.stslex.core.core

import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Logger.exception(throwable)
}

expect fun randomUuid(): String