package com.stslex.wizard.feature.match.ui.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.Config.BottomBar.Match.Type
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.match.di.MatchScope
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.State
import com.stslex.wizard.feature.match.ui.mvi.handlers.CommonHandler
import com.stslex.wizard.feature.match.ui.mvi.handlers.MatchComponent
import com.stslex.wizard.feature.match.ui.mvi.handlers.PagingHandler
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(MatchScope::class)
@Scoped
@Qualifier(MatchScope::class)
internal class MatchStoreImpl(
    component: MatchComponent,
    appDispatcher: AppDispatcher,
    commonHandler: CommonHandler,
    pagingHandler: PagingHandler,
    userStore: UserStore
) : MatchHandlerStore, BaseStore<State, Action, Event, MatchHandlerStore>(
    name = "MatchStore",
    appDispatcher = appDispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Common -> commonHandler
            is Action.Paging -> pagingHandler
            is Action.Navigation -> component
        }
    },
    initialState = State.init(
        uuid = component.uuid.ifBlank { userStore.uuid },
        isSelf = component.type == Type.SELF
    ),
    initialActions = listOf(Action.Paging.Init)
)