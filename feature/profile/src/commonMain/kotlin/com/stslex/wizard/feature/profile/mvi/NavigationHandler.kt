package com.stslex.wizard.feature.profile.mvi

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.Screen.Follower.FollowerType.FOLLOWER
import com.stslex.wizard.core.navigation.Screen.Follower.FollowerType.FOLLOWING
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Navigation
import org.koin.core.annotation.Factory

@Factory
class NavigationHandler(
    private val navigator: Navigator
) : Handler<Navigation, ProfileHandlerStore> {

    override fun ProfileHandlerStore.invoke(action: Navigation) {
        when (action) {
            Navigation.LogIn -> navigator.navTo(Screen.Auth)
            Navigation.Back -> navigator.popBack()
            Navigation.Settings -> navigator.navTo(Screen.Settings)
            is Navigation.Favourite -> navigator.navTo(Screen.Favourite(action.uuid))
            is Navigation.Following -> navigator.navTo(Screen.Follower(FOLLOWING, action.uuid))
            is Navigation.Followers -> navigator.navTo(Screen.Follower(FOLLOWER, action.uuid))
        }
    }

}