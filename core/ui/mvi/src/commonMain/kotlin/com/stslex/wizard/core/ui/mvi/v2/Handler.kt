package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State
import kotlin.reflect.KClass

abstract class Handler<S : State, A : StoreAction, E : Event, StoreAction : Action>(val actionKClass: KClass<*>) {

    inline fun checkAction(action: StoreAction): Boolean = actionKClass.isInstance(action)

    abstract fun HandlerStore<S, StoreAction, E>.invoke(action: A)

}
