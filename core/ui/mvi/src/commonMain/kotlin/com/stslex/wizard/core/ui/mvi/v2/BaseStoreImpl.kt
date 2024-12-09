package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State

internal class BaseStoreImpl<S : State, A : Action, E : Event>(
    initialState: S,
    handlers: Set<Handler<S, *, E, A>>
) : BaseStore<S, A, E>(
    initialState = initialState,
    handlers = handlers
)