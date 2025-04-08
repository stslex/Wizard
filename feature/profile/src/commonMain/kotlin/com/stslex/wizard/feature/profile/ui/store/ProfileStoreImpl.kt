package com.stslex.wizard.feature.profile.ui.store

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
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(ProfileScope::class)
@Scoped
internal class ProfileStoreImpl(
    initStorageHandler: InitStorageHandler,
    logoutHandler: LogoutHandler,
    repeatLastActionHandler: RepeatLastActionHandler,
    clickersHandler: ClickersHandler,
    component: ProfileComponent
) : ProfileHandlerStore, BaseStore<State, Action, Event, ProfileHandlerStore>(
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
    initialActions = listOf(Action.Init),
)

