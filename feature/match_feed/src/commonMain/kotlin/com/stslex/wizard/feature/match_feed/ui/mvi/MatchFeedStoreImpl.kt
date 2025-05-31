package com.stslex.wizard.feature.match_feed.ui.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.match_feed.di.MatchDetailsScope
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.State
import com.stslex.wizard.feature.match_feed.ui.mvi.handlers.CommonHandler
import com.stslex.wizard.feature.match_feed.ui.mvi.handlers.MatchDetailsComponent
import com.stslex.wizard.feature.match_feed.ui.mvi.handlers.UserActionsHandler
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(MatchDetailsScope::class)
@Scoped
@Qualifier(MatchDetailsScope::class)
internal class MatchFeedStoreImpl(
    appDispatcher: AppDispatcher,
    component: MatchDetailsComponent,
    commonHandler: CommonHandler,
    userActionsHandler: UserActionsHandler
) : MatchFeedHandlerStore, BaseStore<State, Action, Event, MatchFeedHandlerStore>(
    name = "MatchFeedStore",
    appDispatcher = appDispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Navigation -> component
            is Action.User -> userActionsHandler
            is Action.Common -> commonHandler
        }
    },
    initialState = State.init(component.uuid),
    initialActions = listOf(Action.Common.Init)
)