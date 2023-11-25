package com.stslex.feature.home.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.home.navigation.HomeScreenRouter
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.Action
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.Event
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.Navigation
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.State

class HomeScreenStore(
    appDispatcher: AppDispatcher,
    router: HomeScreenRouter
) : HomeScreenStoreComponent, BaseStore<State, Event, Action, Navigation>(
    router = router,
    initialState = State.INITIAL,
    appDispatcher = appDispatcher
) {
    override fun sendAction(action: Action) {
        when (action) {
            Action.OnClick -> navigate(Navigation.SecondScreen(state.value.text))
        }
    }
}