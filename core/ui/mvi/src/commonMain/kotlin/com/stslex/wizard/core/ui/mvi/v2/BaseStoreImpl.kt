package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State

internal class BaseStoreImpl<S : State, A : Action, E : Event, HStore : HandlerStore<S, A, E>>(
    initialState: S,
    handlerCreator: HandlerCreator<S, A, E, HStore>,
) : BaseStore<S, A, E, HStore>(
    initialState = initialState,
    handlerCreator = handlerCreator
)