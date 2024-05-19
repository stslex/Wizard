package com.stslex.feature.follower.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.feature.follower.ui.store.FollowerStoreComponent

class FollowerRouterImpl(
    private val navigator: AppNavigator
) : FollowerRouter {

    override fun invoke(event: FollowerStoreComponent.Navigation) {
        TODO("Not yet implemented")
    }
}