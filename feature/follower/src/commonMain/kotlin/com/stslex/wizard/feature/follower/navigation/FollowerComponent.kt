package com.stslex.wizard.feature.follower.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component

interface FollowerComponent : Component {

    companion object {

        fun ComponentContext.createFollowerComponent(): FollowerComponent = FollowerComponentImpl(
            context = this
        )
    }
}