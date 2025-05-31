package com.stslex.wizard.feature.match_feed.ui.mvi.handlers

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match_feed.di.MatchDetailsScope
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedHandlerStore
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(MatchDetailsScope::class)
@Scoped
class UserActionsHandler : Handler<Action.User, MatchFeedHandlerStore> {

    override fun MatchFeedHandlerStore.invoke(action: Action.User) {
        when (action) {
            is Action.User.FilmClick -> actionFilmClick(action)
            is Action.User.FilmSwiped -> actionFilmSwiped(action)
        }
    }

    private fun MatchFeedHandlerStore.actionFilmSwiped(action: Action.User.FilmSwiped) {
        // todo send action to backend
    }

    private fun MatchFeedHandlerStore.actionFilmClick(action: Action.User.FilmClick) {
        consume(Action.Navigation.Film(action.uuid))
    }

}