package com.stslex.wizard.feature.profile.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.navigation.Config.BottomBar.Profile.Type
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Navigation

interface ProfileComponent : Component, Handler<Navigation, ProfileHandlerStore> {

    val type: Type

    val uuid: String

    companion object {

        fun ComponentContext.createProfileComponent(
            type: Type,
            uuid: String,
            navTo: (Config) -> Unit,
            popBack: () -> Unit,
        ): ProfileComponent = ProfileComponentImpl(
            context = this,
            type = type,
            uuid = uuid,
            navTo = navTo,
            popBack = popBack
        )
    }
}

