package com.stslex.wizard.core.core.coroutine

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.AppDispatcherImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface AppCoroutineScope {

    /**
     * Launches a coroutine and catches exceptions. The coroutine is launched on the default dispatcher of the AppDispatcher.
     * @param onError - error handler
     * @param onSuccess - success handler
     * @param action - action to be executed
     * @return Job
     * @see Job
     * @see AppDispatcher
     * */
    fun <T> launch(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: suspend CoroutineScope.(T) -> Unit = {},
        workDispatcher: CoroutineDispatcher = AppDispatcherImpl.default,
        eachDispatcher: CoroutineDispatcher = AppDispatcherImpl.main.immediate,
        action: suspend CoroutineScope.() -> T,
    ): Job

    /**
     * Launches a flow and collects it in the screenModelScope. The flow is collected on the default dispatcher. of the AppDispatcher.
     * @param onError - error handler
     * @param each - action for each element of the flow
     * @return Job
     * @see Flow
     * @see Job
     * @see AppDispatcher
     * */
    fun <T> launch(
        flow: Flow<T>,
        workDispatcher: CoroutineDispatcher = AppDispatcherImpl.default,
        eachDispatcher: CoroutineDispatcher = AppDispatcherImpl.main.immediate,
        onError: suspend (cause: Throwable) -> Unit = {},
        each: suspend (T) -> Unit,
    ): Job
}

