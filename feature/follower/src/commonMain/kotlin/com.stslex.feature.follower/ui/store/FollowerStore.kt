package com.stslex.feature.follower.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.follower.domain.interactor.FollowerInteractor
import com.stslex.feature.follower.navigation.FollowerRouter
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Action
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Event
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.Navigation
import com.stslex.feature.follower.ui.store.FollowerStoreComponent.State

class FollowerStore(
    private val interactor: FollowerInteractor,
    router: FollowerRouter,
    appDispatcher: AppDispatcher,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
        }
    }

    private fun actionInit(action: Action.Init) {
        TODO()
    }
}
