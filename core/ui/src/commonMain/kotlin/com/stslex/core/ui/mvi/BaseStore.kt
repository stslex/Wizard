package com.stslex.core.ui.mvi

import com.stslex.core.core.AppDispatcher
import com.stslex.core.core.Logger
import com.stslex.core.core.coroutineExceptionHandler
import com.stslex.core.ui.navigation.Router
import com.stslex.core.ui.mvi.Store.Action
import com.stslex.core.ui.mvi.Store.Event
import com.stslex.core.ui.mvi.Store.Navigation
import com.stslex.core.ui.mvi.Store.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStore<S : State, E : Event, A : Action, N : Navigation>(
    private val router: Router<N>,
    private val appDispatcher: AppDispatcher,
    initialState: S
) {

    abstract fun sendAction(action: A)

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val scope: CoroutineScope = CoroutineScope(appDispatcher.default)

    private fun exceptionHandler(
        onError: suspend (cause: Throwable) -> Unit = {},
    ) = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.exception(throwable)
        CoroutineScope(coroutineContext).launch(coroutineExceptionHandler) {
            onError(throwable)
        }
    }

    val event: MutableSharedFlow<E> = MutableSharedFlow()

    fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    fun sendEvent(event: E) {
        scope.launch(appDispatcher.default) {
            this@BaseStore.event.emit(event)
        }
    }

    fun navigate(event: N) {
        router(event)
    }

    fun launch(
        onError: suspend (Throwable) -> Unit = {},
        block: suspend CoroutineScope.() -> Unit,
    ): Job = scope.launch(
        context = exceptionHandler(onError),
        block = block
    )

    fun <T> launch(
        action: suspend CoroutineScope.() -> T,
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit,
    ): Job = scope.launch(
        context = exceptionHandler(onError),
        block = {
            onSuccess(action())
        }
    )

    fun <T> Flow<T>.launch(
        onError: suspend (cause: Throwable) -> Unit = {},
        each: suspend (T) -> Unit
    ): Job = this
        .onEach(each)
        .flowOn(exceptionHandler(onError))
        .launchIn(scope)
}