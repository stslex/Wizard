package com.stslex.wizard.core.ui.mvi.handler

import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.core.ui.mvi.handler.Handler.Event

fun interface Handler<in E : Event> {

    operator fun invoke(event: E)

    interface Event : Store.Event
}
