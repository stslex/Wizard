package com.stslex.wizard.feature.settings.domain

import com.stslex.wizard.core.network.api.utils.token.AuthController
import com.stslex.wizard.feature.settings.di.SettingsScope
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(SettingsScope::class)
@Scoped
internal class SettingsInteractorImpl(
    private val authController: AuthController
) : SettingsInteractor {

    override suspend fun logOut() {
        authController.logout()
    }
}