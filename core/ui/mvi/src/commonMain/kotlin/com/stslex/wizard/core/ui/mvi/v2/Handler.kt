package com.stslex.wizard.core.ui.mvi.v2

import com.stslex.wizard.core.ui.mvi.Store.Action

fun interface Handler<A : Action, TStore : HandlerStore<*, *, *>> {

    fun TStore.invoke(action: A)

}
