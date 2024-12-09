package com.stslex.wizard.feature.profile.ui.store

import com.stslex.wizard.core.database.store.UserStore
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.wizard.feature.profile.mvi.InitStorageHandler
import com.stslex.wizard.feature.profile.mvi.LogoutHandler
import com.stslex.wizard.feature.profile.mvi.NavigationHandler
import com.stslex.wizard.feature.profile.mvi.RepeatLastActionHandler
import com.stslex.wizard.feature.profile.mvi.clickersHandler
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

class ProfileStoreImpl(
    interactor: ProfileInteractor,
    userStore: UserStore,
    navigator: Navigator
) : ProfileStore, BaseStore<State, Action, Event>(
    initialState = State.INITIAL,
    handlers = setOf(
        InitStorageHandler(interactor, userStore),
        LogoutHandler(interactor),
        RepeatLastActionHandler(),
        clickersHandler(),
        NavigationHandler(navigator)
    )
)

