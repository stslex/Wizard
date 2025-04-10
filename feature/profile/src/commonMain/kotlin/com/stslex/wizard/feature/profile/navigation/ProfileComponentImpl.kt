package com.stslex.wizard.feature.profile.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.navigation.Config.BottomBar.Profile.Type
import com.stslex.wizard.core.navigation.Config.Follower.FollowerType.FOLLOWER
import com.stslex.wizard.core.navigation.Config.Follower.FollowerType.FOLLOWING
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Navigation

internal class ProfileComponentImpl(
    context: ComponentContext,
    override val type: Type,
    override val uuid: String,
    private val navTo: (Config) -> Unit,
    private val popBack: () -> Unit,
) : ProfileComponent, ComponentContext by context {

    override fun ProfileHandlerStore.invoke(action: Navigation) {
        when (action) {
            Navigation.LogIn -> navTo(Config.Auth)
            Navigation.Back -> popBack()
            Navigation.Settings -> navTo(Config.Settings)
            is Navigation.Favourite -> navTo(Config.Favourite(action.uuid))
            is Navigation.Following -> navTo(Config.Follower(FOLLOWING, action.uuid))
            is Navigation.Followers -> navTo(Config.Follower(FOLLOWER, action.uuid))
        }
    }
}