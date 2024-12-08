package com.stslex.wizard.core.ui.mvi.handler

import com.stslex.wizard.core.ui.mvi.Store
import kotlinx.coroutines.flow.StateFlow

abstract class BaseHandler<in E : Handler.Event, S : Store.State, in A : Store.Action>(
    private val store: Store<S, A, E>
) : Handler<E> {

    val state: StateFlow<S>
        get() = store.state

    protected fun sendAction(action: A) {
        store.sendAction(action)
    }

}
