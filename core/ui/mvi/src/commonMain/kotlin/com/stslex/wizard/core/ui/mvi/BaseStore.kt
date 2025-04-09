package com.stslex.wizard.core.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.AppDispatcherImpl
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.core.coroutine.AppCoroutineScope
import com.stslex.wizard.core.core.coroutine.AppCoroutineScopeImpl
import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStore<S : State, A : Action, E : Event>(
    initialState: S
) : ViewModel(), Store<S, A, E> {

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()
    override val event: SharedFlow<E> = _event.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state.asStateFlow()

    protected val scope: AppCoroutineScope = AppCoroutineScopeImpl(viewModelScope)

    protected val logger = Logger.tag("Store")

    private var _lastAction: A? = null
    protected val lastAction: A?
        get() = _lastAction

    override fun consume(action: A) {
        logger.v("consume: $action")
        if (lastAction != action && action !is Action.RepeatLast) {
            _lastAction = action
        }
        process(action)
    }

    /** Process the action. This method should be overridden in the child class.*/
    protected abstract fun process(action: A)

    /**
     * Updates the state of the screen.
     * @param update - function that updates the state
     * */
    protected fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    /**
     * Sends an event to the screen. The event is sent on the default dispatcher of the AppDispatcher.
     * @param event - event to be sent
     * @see AppDispatcher
     * */
    protected fun sendEvent(event: E) {
        viewModelScope.launch { _event.emit(event) }
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
        workDispatcher: CoroutineDispatcher = AppDispatcherImpl.default,
        eachDispatcher: CoroutineDispatcher = AppDispatcherImpl.main.immediate,
        action: suspend CoroutineScope.() -> T,
    ): Job = scope.launch(
        onError = onError,
        workDispatcher = workDispatcher,
        eachDispatcher = eachDispatcher,
        onSuccess = onSuccess,
        action = action
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
    protected fun <T> Flow<T>.launch(
        onError: suspend (cause: Throwable) -> Unit = {},
        workDispatcher: CoroutineDispatcher = AppDispatcherImpl.default,
        eachDispatcher: CoroutineDispatcher = AppDispatcherImpl.main.immediate,
        each: suspend (T) -> Unit
    ): Job = scope.launch(
        flow = this,
        workDispatcher = workDispatcher,
        eachDispatcher = eachDispatcher,
        onError = onError,
        each = each,
    )
}