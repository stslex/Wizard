package com.stslex.feature.feed.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.feed.navigation.FeedScreenRouter
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Action
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Event
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.Navigation
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent.State

class FeedScreenStore(
    appDispatcher: AppDispatcher,
    router: FeedScreenRouter
) : FeedScreenStoreComponent, BaseStore<State, Event, Action, Navigation>(
    router = router,
    initialState = State.INITIAL,
    appDispatcher = appDispatcher
) {
    override fun sendAction(action: Action) {
        when (action) {
        }
    }
}