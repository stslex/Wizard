package com.stslex.wizard.feature.follower.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.follower.di.FollowerScope
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.mvi.FollowerStore.State
import com.stslex.wizard.feature.follower.mvi.handlers.CommonHandler
import com.stslex.wizard.feature.follower.mvi.handlers.FollowerComponent
import com.stslex.wizard.feature.follower.mvi.handlers.PagingHandler
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(FollowerScope::class)
@Scoped
@Qualifier(FollowerScope::class)
internal class FollowerStoreImpl(
    appDispatcher: AppDispatcher,
    component: FollowerComponent,
    commonHandler: CommonHandler,
    pagingHandler: PagingHandler,
) : FollowerHandlerStore, BaseStore<State, Action, FollowerStore.Event, FollowerHandlerStore>(
    name = "FollowerStore",
    appDispatcher = appDispatcher,
    initialState = State.initial(
        type = component.followerType,
        uuid = component.uuid
    ),
    handlerCreator = { action ->
        when (action) {
            is Action.Common -> commonHandler
            is Action.Paging -> pagingHandler
            is Action.Navigation -> component
        }
    },
    initialActions = listOf(Action.Paging.Init)
)