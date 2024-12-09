package com.stslex.wizard.feature.profile.mvi


import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.core.ui.mvi.v2.HandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.RepeatLastAction
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

class RepeatLastActionHandler :
    Handler<State, RepeatLastAction, Event, Action>(RepeatLastAction::class) {

    override fun HandlerStore<State, Action, Event>.invoke(action: RepeatLastAction) {
        val lastAction = lastAction ?: return
        updateState { currentState ->
            val screen = when (val screen = currentState.screen) {
                is ProfileScreenState.Content -> ProfileScreenState.Content.Loading(screen.data)
                is ProfileScreenState.Error, is ProfileScreenState.Shimmer -> ProfileScreenState.Shimmer
            }
            currentState.copy(screen = screen)
        }
        sendAction(lastAction)
    }
}