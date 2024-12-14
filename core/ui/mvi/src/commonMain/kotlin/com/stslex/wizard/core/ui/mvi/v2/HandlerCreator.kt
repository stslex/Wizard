package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State

fun interface HandlerCreator<S : State, A : Action, E : Event, HStore : HandlerStore<S, A, E>> {

    operator fun invoke(action: A): Handler<*, HStore>
}