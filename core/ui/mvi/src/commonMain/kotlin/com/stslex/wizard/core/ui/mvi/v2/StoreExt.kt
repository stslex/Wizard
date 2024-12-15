package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.Store.Action
import com.stslex.wizard.core.ui.mvi.Store.Event
import com.stslex.wizard.core.ui.mvi.Store.State

@Suppress("UNCHECKED_CAST")
fun <S : State, A : Action, E : Event, TStore : Store<S, A, E>, HStore : HandlerStore<S, A, E>> store(
    initialState: S,
    handlerCreator: HandlerCreator<S, A, E, HStore>
): TStore = BaseStoreImpl(initialState, handlerCreator) as TStore

fun <S : State, A : Action, E : Event, HStore : HandlerStore<S, A, E>> Handler<A, HStore>.invoke(
    store: HStore,
    action: A
) {
    with(store) {
        this.invoke(action)
    }
}

inline fun <S : State, A : Action, E : Event, HStore : HandlerStore<S, A, E>> handler(
    crossinline block: HandlerStore<S, A, E>.(action: A) -> Unit
) = Handler<A, HStore> { block(it) }
