package com.stslex.wizard.feature.profile.ui.mvi.handlers

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.di.ProfileScope
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.mvi.store.ProfileStore.Action
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(ProfileScope::class)
@Scoped
class ClickersHandler : Handler<Action.Click, ProfileHandlerStore> {

    override fun ProfileHandlerStore.invoke(action: Action.Click) {
        when (action) {
            Action.Click.BackButtonClick -> consume(Action.Navigation.Back)
            Action.Click.FavouriteClick -> consume(Action.Navigation.Favourite(state.value.uuid))
            Action.Click.FollowersClick -> consume(Action.Navigation.Followers(state.value.uuid))
            Action.Click.FollowingClick -> consume(Action.Navigation.Following(state.value.uuid))
            Action.Click.SettingsClick -> consume(Action.Navigation.Settings)
        }
    }
}
