package com.stslex.core.ui.mvi

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.stslex.core.core.AppDispatcher
import com.stslex.core.core.Logger
import com.stslex.core.core.coroutineExceptionHandler
import com.stslex.core.ui.mvi.Store.Action
import com.stslex.core.ui.mvi.Store.Event
import com.stslex.core.ui.mvi.Store.Navigation
import com.stslex.core.ui.mvi.Store.State
import com.stslex.core.ui.navigation.Router
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStore<S : State, E : Event, A : Action, N : Navigation>(
    private val router: Router<N>,
    private val appDispatcher: AppDispatcher,
    initialState: S
) : Store, StateScreenModel<S>(initialState) {

    abstract fun sendAction(action: A)

    private fun exceptionHandler(
        onError: suspend (cause: Throwable) -> Unit = {},
    ) = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.exception(throwable)
        CoroutineScope(
            context = coroutineContext + appDispatcher.default
        ).launch(coroutineExceptionHandler) {
            onError(throwable)
        }
    }

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()
    val event: SharedFlow<E> = _event.asSharedFlow()

    fun updateState(update: (S) -> S) {
        mutableState.update(update)
    }

    fun sendEvent(event: E) {
        screenModelScope.launch(appDispatcher.default) {
            this@BaseStore._event.emit(event)
        }
    }

    fun navigate(event: N) {
        router(event)
    }

    fun launch(
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: suspend () -> Unit = {},
        block: suspend CoroutineScope.() -> Unit,
    ): Job = screenModelScope.launch(appDispatcher.default) {
        runCatching { block() }
            .onSuccess { onSuccess() }
            .onFailure {
                Logger.exception(it)
                onError(it)
            }
    }

    fun <T> launch(
        action: suspend CoroutineScope.() -> T,
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit,
    ): Job = screenModelScope.launch(
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            onSuccess(action())
        }
    )

    fun <T> Flow<T>.launchFlow(
        onError: suspend (cause: Throwable) -> Unit = {},
        each: suspend (T) -> Unit
    ): Job = screenModelScope.launch(
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            collect(each)
        }
    )
}