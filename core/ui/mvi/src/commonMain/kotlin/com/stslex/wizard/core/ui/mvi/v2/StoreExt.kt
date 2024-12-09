package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State

@Suppress("UNCHECKED_CAST")
fun <S : State, A : Action, E : Event, TStore : Store<S, A, E>> store(
    initialState: S,
    handlers: Set<Handler<S, *, E, A>>
): TStore = BaseStoreImpl(initialState, handlers) as TStore

fun <S : State, A : StoreAction, E : Event, StoreAction : Action> Handler<S, A, E, StoreAction>.invoke(
    store: HandlerStore<S, StoreAction, E>,
    action: A
) {
    with(store) {
        this.invoke(action)
    }
}

inline fun <S : State, reified A : StoreAction, E : Event, StoreAction : Action> handler(
    crossinline block: HandlerStore<S, StoreAction, E>.(action: A) -> Unit
) = object : Handler<S, A, E, StoreAction>(A::class) {

    override fun HandlerStore<S, StoreAction, E>.invoke(action: A) {
        block(action)
    }
}
