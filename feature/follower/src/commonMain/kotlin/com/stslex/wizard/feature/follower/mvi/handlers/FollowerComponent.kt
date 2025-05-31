package com.stslex.wizard.feature.follower.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.follower.mvi.FollowerHandlerStore
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action

interface FollowerComponent : Component, Handler<Action.Navigation, FollowerHandlerStore> {

    val followerType: Config.Follower.FollowerType
    val uuid: String

    companion object {

        fun ComponentContext.createFollowerComponent(
            followerType: Config.Follower.FollowerType,
            uuid: String
        ): FollowerComponent = FollowerComponentImpl(
            context = this,
            followerType = followerType,
            uuid = uuid
        )
    }
}