package com.stslex.wizard.core.core.coroutine

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.core.coroutineExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppCoroutineScopeImpl(
    private val scope: CoroutineScope,
    private val appDispatcher: AppDispatcher
) : AppCoroutineScope {

    private fun exceptionHandler(
        onError: suspend (cause: Throwable) -> Unit = {},
    ) = CoroutineExceptionHandler { _, throwable ->
        Logger.e(throwable)
        scope.launch(appDispatcher.default + coroutineExceptionHandler) {
            onError(throwable)
        }
    }

    override fun <T> launch(
        start: CoroutineStart,
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(T) -> Unit,
        action: suspend CoroutineScope.() -> T
    ): Job = scope.launch(
        start = start,
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            onSuccess(action())
        }
    )

    override fun <T> launch(
        flow: Flow<T>,
        onError: suspend (cause: Throwable) -> Unit,
        each: suspend (T) -> Unit
    ): Job = scope.launch(
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            flow.collect(each)
        }
    )
}