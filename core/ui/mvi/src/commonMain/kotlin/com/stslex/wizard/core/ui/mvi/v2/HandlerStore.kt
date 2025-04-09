package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.AppDispatcherImpl
import com.stslex.wizard.core.core.AppLogger
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface HandlerStore<S : State, A : Store.Action, E : Event> {

    val state: StateFlow<S>

    val lastAction: A?

    val logger: AppLogger

    fun sendEvent(event: E)

    fun consume(action: A)

    fun updateState(update: (S) -> S)

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
    fun <T> Flow<T>.launch(
        onError: suspend (cause: Throwable) -> Unit = {},
        workDispatcher: CoroutineDispatcher = AppDispatcherImpl.default,
        eachDispatcher: CoroutineDispatcher = AppDispatcherImpl.main.immediate,
        each: suspend (T) -> Unit
    ): Job
}