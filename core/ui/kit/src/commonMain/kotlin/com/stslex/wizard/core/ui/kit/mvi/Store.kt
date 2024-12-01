package com.stslex.wizard.core.ui.kit.mvi

import com.stslex.wizard.core.ui.kit.mvi.Store.Action
import com.stslex.wizard.core.ui.kit.mvi.Store.Event
import com.stslex.wizard.core.ui.kit.mvi.Store.State
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<out S : State, in A : Action, out E : Event> {

    /** Flow of the state of the screen. */
    val state: StateFlow<S>

    /** Flow of events that are sent to the screen. */
    val event: SharedFlow<E>

    /**
     * Sends an action to the store. Checks if the action is not the same as the last action.
     * If the action is not the same as the last action, the last action is updated.
     * The action is then processed.
     * @param action - action to be sent
     */
    fun sendAction(action: A)

    interface State

    interface Event

    interface Action {

        interface RepeatLast : Action

        interface Navigation : Action
    }
}

