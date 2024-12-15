package com.stslex.wizard.feature.profile.mvi

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action

class ClickersHandler : Handler<Action.Click, ProfileHandlerStore> {

    override fun ProfileHandlerStore.invoke(action: Action.Click) {
        when (action) {
            Action.Click.BackButtonClick -> sendAction(Action.Navigation.Back)
            Action.Click.FavouriteClick -> sendAction(Action.Navigation.Favourite(state.value.uuid))
            Action.Click.FollowersClick -> sendAction(Action.Navigation.Followers(state.value.uuid))
            Action.Click.FollowingClick -> sendAction(Action.Navigation.Following(state.value.uuid))
            Action.Click.SettingsClick -> sendAction(Action.Navigation.Settings)
        }
    }
}
