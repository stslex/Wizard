package com.stslex.wizard.feature.profile.mvi

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.core.ui.mvi.v2.handler
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Click
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State

fun clickersHandler(): Handler<State, Click, Event, Action> = handler { action ->
    when (action) {
        Click.BackButtonClick -> sendAction(Action.Navigation.Back)
        Click.FavouriteClick -> sendAction(Action.Navigation.Favourite(state.value.uuid))
        Click.FollowersClick -> sendAction(Action.Navigation.Followers(state.value.uuid))
        Click.FollowingClick -> sendAction(Action.Navigation.Following(state.value.uuid))
        Click.SettingsClick -> sendAction(Action.Navigation.Settings)
    }
}
