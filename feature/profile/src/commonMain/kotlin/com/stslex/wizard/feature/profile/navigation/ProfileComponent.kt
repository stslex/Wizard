package com.stslex.wizard.feature.profile.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.profile.ui.store.ProfileHandlerStore
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action.Navigation

interface ProfileComponent : Component, Handler<Navigation, ProfileHandlerStore> {

    companion object {

        fun ComponentContext.createProfileComponent(
            navTo: (Config) -> Unit,
            popBack: () -> Unit,
        ): ProfileComponent = ProfileComponentImpl(
            context = this,
            navTo = navTo,
            popBack = popBack
        )
    }
}

