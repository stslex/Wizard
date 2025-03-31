package com.stslex.wizard.feature.profile.mvi


import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.di.ProfileScope
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileScreenState
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(ProfileScope::class)
@Scoped
class RepeatLastActionHandler : Handler<Action.RepeatLastAction, ProfileHandlerStore> {

    override fun ProfileHandlerStore.invoke(action: Action.RepeatLastAction) {
        val lastAction = lastAction ?: return
        updateState { currentState ->
            val screen = when (val screen = currentState.screen) {
                is ProfileScreenState.Content -> ProfileScreenState.Content.Loading(screen.data)
                is ProfileScreenState.Error, is ProfileScreenState.Shimmer -> ProfileScreenState.Shimmer
            }
            currentState.copy(screen = screen)
        }
        consume(lastAction)
    }
}