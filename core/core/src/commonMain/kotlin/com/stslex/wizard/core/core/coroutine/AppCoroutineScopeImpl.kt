package com.stslex.wizard.core.core.coroutine

import com.stslex.wizard.core.core.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// todo: add dispatchers from di
class AppCoroutineScopeImpl(private val scope: CoroutineScope) : AppCoroutineScope {

    private fun exceptionHandler(
        eachDispatcher: CoroutineDispatcher,
        onError: suspend (cause: Throwable) -> Unit = {},
    ) = CoroutineExceptionHandler { _, throwable ->
        Logger.e(throwable)
        scope.launch(eachDispatcher) {
            onError(throwable)
        }
    }

    override fun <T> launch(
        start: CoroutineStart,
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(T) -> Unit,
        workDispatcher: CoroutineDispatcher,
        eachDispatcher: CoroutineDispatcher,
        action: suspend CoroutineScope.() -> T,
    ): Job = scope.launch(
        start = start,
        context = exceptionHandler(eachDispatcher, onError) + workDispatcher,
        block = {
            val result = action()
            withContext(eachDispatcher) {
                onSuccess(result)
            }
        }
    )

    override fun <T> launch(
        flow: Flow<T>,
        workDispatcher: CoroutineDispatcher,
        eachDispatcher: CoroutineDispatcher,
        onError: suspend (cause: Throwable) -> Unit,
        each: suspend (T) -> Unit
    ): Job = scope.launch(
        context = exceptionHandler(eachDispatcher, onError) + workDispatcher,
        block = {
            flow.collect {
                withContext(eachDispatcher) {
                    each(it)
                }
            }
        }
    )
}