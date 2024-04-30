package com.stslex.feature.follower.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.feature.follower.ui.store.FollowerStore

class FollowerRouterImpl(
    private val navigator: AppNavigator
) : FollowerRouter {

    override fun invoke(event: FollowerStore.Navigation) {
        TODO("Not yet implemented")
    }
}