package com.stslex.wizard.feature.favourite.mvi.handler

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FavouriteScope::class)
@Scoped
class ActionInputSearchHandler : Handler<Action.InputSearch, FavouriteHandlerStore> {

    override fun FavouriteHandlerStore.invoke(action: Action.InputSearch) {
        updateState { state -> state.copy(query = action.query) }
    }
}