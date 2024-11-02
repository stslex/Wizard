package com.stslex.wizard.feature.settings.domain

import com.stslex.wizard.core.network.utils.token.AuthController

class SettingsInteractorImpl(
    private val authController: AuthController
) : SettingsInteractor {

    override suspend fun logOut() {
        authController.logout()
    }
}