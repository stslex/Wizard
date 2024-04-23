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

    private var _lastAction: A? = null
    protected val lastAction: A?
        get() = _lastAction

    /**
     * Sends an action to the store. Checks if the action is not the same as the last action.
     * If the action is not the same as the last action, the last action is updated.
     * The action is then processed.
     * @param action - action to be sent
     */
    fun sendAction(action: A) {
        if (lastAction != action && action !is Action.RepeatLastAction) {
            _lastAction = action
        }
        process(action)
    }

    /**
     * Process the action. This method should be overridden in the child class.
     */
    protected abstract fun process(action: A)

    private fun exceptionHandler(
        onError: suspend (cause: Throwable) -> Unit = {},
    ) = CoroutineExceptionHandler { _, throwable ->
        Logger.exception(throwable)
        screenModelScope.launch(appDispatcher.default + coroutineExceptionHandler) {
            onError(throwable)
        }
    }

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()

    /**
     * Flow of events that are sent to the screen.
     * */
    val event: SharedFlow<E> = _event.asSharedFlow()

    /**
     * Updates the state of the screen.
     * @param update - function that updates the state
     * */
    protected fun updateState(update: (S) -> S) {
        mutableState.update(update)
    }

    /**
     * Sends an event to the screen. The event is sent on the default dispatcher of the AppDispatcher.
     * @param event - event to be sent
     * @see AppDispatcher
     * */
    protected fun sendEvent(event: E) {
        screenModelScope.launch(appDispatcher.default) {
            this@BaseStore._event.emit(event)
        }
    }

    /**
     * Navigates to the specified screen. The router is called with the specified event.
     * @param event - event to be passed to the router
     * @see Router
     * */
    protected fun navigate(event: N) {
        router(event)
    }

    /**
     * Launches a coroutine and catches exceptions. The coroutine is launched on the default dispatcher of the AppDispatcher.
     * @param onError - error handler
     * @param onSuccess - success handler
     * @param action - action to be executed
     * @return Job
     * @see Job
     * @see AppDispatcher
     * */
    protected fun <T> launch(
        onError: suspend (Throwable) -> Unit = {},
        onSuccess: suspend CoroutineScope.(T) -> Unit = {},
        action: suspend CoroutineScope.() -> T,
    ): Job = screenModelScope.launch(
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            onSuccess(action())
        }
    )

    /**
     * Launches a flow and collects it in the screenModelScope. The flow is collected on the default dispatcher. of the AppDispatcher.
     * @param onError - error handler
     * @param each - action for each element of the flow
     * @return Job
     * @see Flow
     * @see Job
     * @see AppDispatcher
     * */
    protected fun <T> Flow<T>.launchFlow(
        onError: suspend (cause: Throwable) -> Unit = {},
        each: suspend (T) -> Unit
    ): Job = screenModelScope.launch(
        context = exceptionHandler(onError) + appDispatcher.default,
        block = {
            collect(each)
        }
    )
}