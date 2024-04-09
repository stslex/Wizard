package com.stslex.feature.settings.domain

import com.stslex.core.network.utils.token.AuthController

class SettingsInteractorImpl(
    private val authController: AuthController
) : SettingsInteractor {

    override suspend fun logOut() {
        authController.logOut()
    }
}