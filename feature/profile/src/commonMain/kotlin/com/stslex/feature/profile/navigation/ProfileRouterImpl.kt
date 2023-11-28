package com.stslex.feature.profile.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.feature.profile.ui.store.ProfileStoreComponent

class ProfileRouterImpl(
    private val navigator: AppNavigator
) : ProfileRouter {

    override fun invoke(
        event: ProfileStoreComponent.Navigation
    ) {
        TODO("Not yet implemented")
    }
}