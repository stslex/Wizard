package com.stslex.wizard.feature.settings.di

import com.stslex.wizard.core.ui.mvi.storeDefinition
import com.stslex.wizard.feature.settings.domain.SettingsInteractor
import com.stslex.wizard.feature.settings.domain.SettingsInteractorImpl
import com.stslex.wizard.feature.settings.navigation.SettingsRouter
import com.stslex.wizard.feature.settings.navigation.SettingsRouterImpl
import com.stslex.wizard.feature.settings.ui.store.SettingsStore
import org.koin.dsl.module

val featureSettingsModule = module {
    storeDefinition {
        SettingsStore(
            interactor = get(),
            appDispatcher = get(),
            router = get(),
        )
    }
    factory<SettingsRouter> { SettingsRouterImpl(navigator = get()) }
    factory<SettingsInteractor> { SettingsInteractorImpl(authController = get()) }
}