package com.stslex.wizard.feature.follower.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config.Follower.FollowerType
import com.stslex.wizard.feature.follower.mvi.FollowerHandlerStore
import com.stslex.wizard.feature.follower.mvi.FollowerStore

internal class FollowerComponentImpl(
    override val followerType: FollowerType,
    override val uuid: String,
    context: ComponentContext
) : FollowerComponent, ComponentContext by context{

    override fun FollowerHandlerStore.invoke(action: FollowerStore.Action.Navigation) {
        TODO("Not yet implemented")
    }
}