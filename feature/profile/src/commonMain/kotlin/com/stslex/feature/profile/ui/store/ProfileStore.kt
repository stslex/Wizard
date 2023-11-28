package com.stslex.feature.profile.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.profile.domain.interactor.ProfileInteractor
import com.stslex.feature.profile.navigation.ProfileRouter
import com.stslex.feature.profile.ui.model.toUi
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Action
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Event
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Navigation
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.State

class ProfileStore(
    private val interactor: ProfileInteractor,
    router: ProfileRouter,
    appDispatcher: AppDispatcher,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    override fun sendAction(action: Action) {
        when (action) {
            is Action.LoadProfile -> actionLoadProfile(action)
        }
    }

    private fun actionLoadProfile(action: Action.LoadProfile) {
        interactor.getProfile(action.uuid)
            .launchFlow { profile ->
                updateState { currentState ->
                    currentState.copy(
                        screen = ProfileScreenState.Content(profile.toUi())
                    )
                }
            }
    }
}
