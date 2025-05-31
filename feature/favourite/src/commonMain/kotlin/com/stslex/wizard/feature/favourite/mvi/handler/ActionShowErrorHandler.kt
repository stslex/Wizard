package com.stslex.wizard.feature.favourite.mvi.handler

import com.stslex.wizard.core.ui.kit.base.mapToAppError
import com.stslex.wizard.core.ui.mvi.CommonEvents.Snackbar
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteScreenState
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FavouriteScope::class)
@Scoped
class ActionShowErrorHandler : Handler<Action.ShowError, FavouriteHandlerStore> {

    override fun FavouriteHandlerStore.invoke(action: Action.ShowError) {
        val appError = action.error.mapToAppError("error logout")
        if (state.value.screen is FavouriteScreenState.Content) {
            sendEvent(
                FavouriteStore.Event.ShowSnackbar(Snackbar.Error(appError.message))
            )
        } else {
            updateState { currentState ->
                currentState.copy(
                    screen = FavouriteScreenState.Error(appError)
                )
            }
        }
    }
}