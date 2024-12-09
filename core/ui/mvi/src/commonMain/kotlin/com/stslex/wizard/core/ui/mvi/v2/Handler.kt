package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State
import kotlin.reflect.KClass

abstract class Handler<S : State, A : StoreAction, E : Event, StoreAction : Action>(val actionKClass: KClass<*>) {

    val handlerName: String = requireNotNull(actionKClass.simpleName) {
        "Action class name is null"
    }

    inline fun checkAction(action: StoreAction): Boolean = actionKClass.isInstance(action)

    abstract fun HandlerStore<S, StoreAction, E>.invoke(action: A)

    override fun toString(): String = handlerName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Handler<*, *, *, *>) return false
        return handlerName == other.handlerName
    }

    override fun hashCode(): Int = handlerName.hashCode()

}
