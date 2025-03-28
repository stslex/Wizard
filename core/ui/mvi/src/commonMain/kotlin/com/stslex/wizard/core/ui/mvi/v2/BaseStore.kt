package com.stslex.wizard.core.ui.mvi.v2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.core.coroutine.AppCoroutineScope
import com.stslex.wizard.core.core.coroutine.AppCoroutineScopeImpl
import com.stslex.wizard.core.ui.mvi.Store
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

open class BaseStore<S : State, A : Action, E : Event, HStore : HandlerStore<S, A, E>>(
    initialState: S,
    private val handlerCreator: HandlerCreator<S, A, E, HStore>,
    vararg initialActions: A,
) : ViewModel(), Store<S, A, E>, HandlerStore<S, A, E> {

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()
    override val event: SharedFlow<E> = _event.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state.asStateFlow()

    protected val scope: AppCoroutineScope = AppCoroutineScopeImpl(viewModelScope)

    private var _lastAction: A? = null
    override val lastAction: A?
        get() = _lastAction

    init {
        initialActions.forEach { consume(it) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun consume(action: A) {
        if (lastAction != action && action !is Action.RepeatLast) {
            _lastAction = action
        }
        val handler = handlerCreator(action) as Handler<A, HStore>
        handler.invoke(this as HStore, action)
    }

    /**
     * Updates the state of the screen.
     * @param update - function that updates the state
     * */
    override fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    /**
     * Sends an event to the screen. The event is sent on the default dispatcher of the AppDispatcher.
     * @param event - event to be sent
     * @see AppDispatcher
     * */
    override fun sendEvent(event: E) {
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
    override fun <T> launch(
        onError: suspend (Throwable) -> Unit,
        onSuccess: suspend CoroutineScope.(T) -> Unit,
        workDispatcher: CoroutineDispatcher,
        eachDispatcher: CoroutineDispatcher,
        action: suspend CoroutineScope.() -> T,
    ) = scope.launch(
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
    override fun <T> Flow<T>.launch(
        onError: suspend (cause: Throwable) -> Unit,
        workDispatcher: CoroutineDispatcher,
        eachDispatcher: CoroutineDispatcher,
        each: suspend (T) -> Unit
    ): Job = scope.launch(
        flow = this,
        workDispatcher = workDispatcher,
        eachDispatcher = eachDispatcher,
        onError = onError,
        each = each,
    )


}