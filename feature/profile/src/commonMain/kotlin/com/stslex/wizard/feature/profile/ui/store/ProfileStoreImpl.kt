package com.stslex.wizard.feature.profile.ui.store

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.profile.di.ProfileScope
import com.stslex.wizard.feature.profile.mvi.ClickersHandler
import com.stslex.wizard.feature.profile.mvi.InitStorageHandler
import com.stslex.wizard.feature.profile.mvi.LogoutHandler
import com.stslex.wizard.feature.profile.mvi.RepeatLastActionHandler
import com.stslex.wizard.feature.profile.navigation.ProfileComponent
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(ProfileScope::class)
@Scoped
@Qualifier(ProfileScope::class)
internal class ProfileStoreImpl(
    initStorageHandler: InitStorageHandler,
    logoutHandler: LogoutHandler,
    repeatLastActionHandler: RepeatLastActionHandler,
    clickersHandler: ClickersHandler,
    component: ProfileComponent,
    appDispatcher: AppDispatcher
) : ProfileHandlerStore, BaseStore<State, Action, Event, ProfileHandlerStore>(
    name = TAG,
    initialState = State.createInitial(component.uuid, component.type),
    handlerCreator = { action ->
        when (action) {
            is Action.Init -> initStorageHandler
            is Action.Logout -> logoutHandler
            is Action.RepeatLastAction -> repeatLastActionHandler
            is Action.Click -> clickersHandler
            is Action.Navigation -> component
        }
    },
    appDispatcher = appDispatcher,
    initialActions = listOf(Action.Init),
) {

    internal companion object {

        private const val TAG = "ProfileStore"
    }
}

