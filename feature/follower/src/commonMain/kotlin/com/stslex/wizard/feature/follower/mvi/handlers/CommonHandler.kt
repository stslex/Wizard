package com.stslex.wizard.feature.follower.mvi.handlers

import com.stslex.wizard.core.ui.kit.base.mapToAppError
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.follower.di.FollowerScope
import com.stslex.wizard.feature.follower.mvi.FollowerHandlerStore
import com.stslex.wizard.feature.follower.mvi.FollowerScreenState
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Event
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FollowerScope::class)
@Scoped
internal class CommonHandler : Handler<Action.Common, FollowerHandlerStore> {

    override fun FollowerHandlerStore.invoke(action: Action.Common) {
        when (action) {
            is Action.Common.OnUserClick -> actionUserClick(action)
            is Action.Common.QueryChanged -> actionQueryChanged(action)
            is Action.Common.ShowError -> showError(action)
        }
    }

    private fun FollowerHandlerStore.actionUserClick(action: Action.Common.OnUserClick) {
        // todo ("navigate to user")
    }

    private fun FollowerHandlerStore.actionQueryChanged(action: Action.Common.QueryChanged) {
        updateState { currentState ->
            currentState.copy(
                query = action.query
            )
        }
    }

    private fun FollowerHandlerStore.showError(action: Action.Common.ShowError) {
        val appError = action.error.mapToAppError("error logout")
        if (state.value.screen is FollowerScreenState.Content) {
            sendEvent(
                Event.ShowSnackbar(Snackbar.Error(appError.message))
            )
        } else {
            updateState { currentState ->
                currentState.copy(
                    screen = FollowerScreenState.Error(appError)
                )
            }
        }
    }
}