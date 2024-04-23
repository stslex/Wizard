package com.stslex.core.core

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Logger.exception(throwable)
}

expect fun randomUuid(): String

suspend fun <T, R> List<T>.asyncMap(
    transform: suspend (T) -> R
): List<R> = coroutineScope {
    map { item -> async { transform(item) } }
}.awaitAll()